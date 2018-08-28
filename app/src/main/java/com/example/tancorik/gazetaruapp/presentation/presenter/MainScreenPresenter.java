package com.example.tancorik.gazetaruapp.presentation.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

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
    private boolean mNewtworkStatus = false;

    public MainScreenPresenter() {
        MyApp.getAppComponent().inject(this);
    }

    public void initPresenter(IMainScreenView view) {
        Log.i("Log_TAG_PRESENTER", "инициализируем презентер " + Boolean.toString(mView == null));
        mView = view;
//        mView.showProgress();
//        mNewsList = new ArrayList<>();
//        initObserver();
//        updateState();
//        if (!checkNetwork()) {
//            mView.showError();
//            return;
//        }
//        if (!mAllNews) {
//            mView = view;
//            mNewsList = new ArrayList<>();
//            mView.showProgress();
//            initObserver();
//
//        }
//        else {
//            mView.showNews(mNewsList);
//        }
    }

    public void clearPresenter() {
        Log.i("Log_TAG_PRESENTER", "Освободить презентер!");
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
            Log.i("Log_TAG_PRESENTER", "Показать прогресс");
            mView.showProgress();
        }
        if (!checkNetwork()) {
            Log.i("Log_TAG_PRESENTER", "Интернет заглох");
            if (mDisposableObserver != null) {
                mDisposableObserver.dispose();
            }
            mNewsList.clear();
            if (mView != null) {
                mView.showError();
            }
        }
        else {
            Log.i("Log_TAG_PRESENTER", "Интернет появился " + Boolean.toString(mView == null));
            initObserver();
            mNewsList.clear();
            loadNews();
        }
    }

    private boolean checkNetwork() {
        Log.i("Log_TAG_PRESENTER", "Проверяем интернет");
        ConnectivityManager connectivityManager =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private void initObserver() {
        Log.i("Log_TAG_PRESENTER", "Инициализируем слушателся в initObserver");
        mDisposableObserver = new DisposableObserver<News>() {
            @Override
            public void onNext(News news) {
                mNewsList.add(news);
                mView.updateDownloadText(news.getChannel().getTitle());
                Log.i("Log_TAG_PRESENTER", news.getChannel().getTitle());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i("Log_TAG_PRESENTER", "Законичили слушать");
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
