package com.example.tancorik.gazetaruapp.presentation.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.tancorik.gazetaruapp.application.MyApp;
import com.example.tancorik.gazetaruapp.data.RemoteRssService;
import com.example.tancorik.gazetaruapp.presentation.model.News;
import com.example.tancorik.gazetaruapp.presentation.view.IMainScreenView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

public class MainScreenPresenter {

    @Inject
    public Context mContext;
    private DisposableObserver<News> mDisposableObserver;
    private IMainScreenView mView;
    private List<News> mNewsList = new ArrayList<>();
    private boolean mAllNews = false;
    private boolean mNetworkStatus = false;

    public MainScreenPresenter() {
        MyApp.getAppComponent().inject(this);
    }

    public void initPresenter(IMainScreenView view) {
        mView = view;
    }

    public void clearPresenter() {
        if (mDisposableObserver != null)
            mDisposableObserver.dispose();
        mAllNews = false;
        mView = null;
    }

    public List<News> getAllNews() {
        return mNewsList;
    }

    public News getNews(int position) {
        return mNewsList.get(position);
    }

    public void updateState() {
        if (mAllNews)
            return;
        else {
            mView.showProgress();
        }
        if (!checkNetwork()) {
            if (mDisposableObserver != null) {
                mDisposableObserver.dispose();
            }
            mNewsList.clear();
            if (mView != null) {
                mView.showError();
            }
            mNetworkStatus = false;
        }
        else if (checkNetwork() && !mNetworkStatus){
            initObserver();
            mNewsList.clear();
            loadNews();
            mNetworkStatus = true;
        }
    }

    private boolean checkNetwork() {
        Log.i("Log_TAG_PRESENTER", "Проверяем интернет");
        ConnectivityManager connectivityManager =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return (networkInfo != null && networkInfo.isConnected());
    }

    private void initObserver() {
        mDisposableObserver = new DisposableObserver<News>() {
            @Override
            public void onNext(News news) {
                mNewsList.add(news);
                mView.updateDownloadText(news.getChannel().getTitle());

            }

            @Override
            public void onError(Throwable e) {
                mNetworkStatus = false;
                updateState();
            }

            @Override
            public void onComplete() {
                mAllNews = true;
                mView.showNews(mNewsList);
                mDisposableObserver.dispose();
            }
        };
    }

    private void loadNews() {
        Log.i("Log_TAG_PRESENTER", "Встаем на прослушку в loadNews");
        Observable<News> observable = new RemoteRssService().getNewsObservable();
        observable.subscribe(mDisposableObserver);

    }


}
