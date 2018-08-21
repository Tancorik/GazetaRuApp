package com.example.tancorik.gazetaruapp.presentation.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tancorik.gazetaruapp.R;
import com.example.tancorik.gazetaruapp.data.RemoteRssService;
import com.example.tancorik.gazetaruapp.domain.IRemoteRssService;
import com.example.tancorik.gazetaruapp.presentation.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {

    private DisposableObserver<NewsModel> mNewsModelDisposableObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final List<NewsModel> newsModelList = new ArrayList<>();

        IRemoteRssService rssService = new RemoteRssService();
        Observable<NewsModel> newsModelObservable = rssService.getNewsObservable();

        mNewsModelDisposableObserver = new DisposableObserver<NewsModel>() {
            @Override
            public void onNext(NewsModel newsModel) {
                newsModelList.add(newsModel);
                TextView textView = findViewById(R.id.text_view);
                textView.setText(newsModelList.get(newsModelList.size()-1).getChannel().getDescription());
            }

            @Override
            public void onError(Throwable e) {
                TextView textView = findViewById(R.id.text_view);
                textView.setText(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        new RemoteRssService().getNewsObservable().subscribe(mNewsModelDisposableObserver);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNewsModelDisposableObserver.dispose();
    }
}
