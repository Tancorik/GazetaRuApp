package com.example.tancorik.gazetaruapp.data;

import android.util.Log;

import com.example.tancorik.gazetaruapp.domain.IRemoteRssService;
import com.example.tancorik.gazetaruapp.presentation.model.NewsModel;

import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * created by Aleksandr Karpachev
 *
 *      21.08.2018
 *
 *  Класс предоставляет информацию о новостях с сайти www.gazeta.ru
 *
 *  Так как рубрик очень много для последовательной загрузки новостей из всех рубрик
 *  предоставляется метод возвращающий Observable<>, который загружает информацию в отдельном потоке.
 *  Если поставить на него слушателя, то можно получить информацию о новостях из всех рубрик.
 *
 *  Список ссылок на рубрики описан в интерфейсе IRemoteRssService.java
 *
 *  Можно получать новости по отдельным рубрикам вызвав метод getNews(), но обратите внимание
 *  что он будет работать в том потоке, в которым вызываете.
 */

public class RemoteRssService implements IRemoteRssService {

    private static final String LOG_TAG = "remoteRssService_LOGS";

    @Override
    public NewsModel getNews(String rssUrl) throws IOException {
        InputStream inputStream = null;
        HttpURLConnection connection = null;
        NewsModel newsModel;
        try {
            URL url = new URL(rssUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                String error = "Error responseCode: +" + responseCode;
                Log.i(LOG_TAG, error);
                throw new IOException(error);
            }
            inputStream = connection.getInputStream();
            if (inputStream == null) {
                Log.i(LOG_TAG, "Error get inputStream");
                throw new IOException("Error get inputStream!");
            }
            Reader reader = new InputStreamReader(inputStream, "windows-1251");
            try {
                newsModel = new Persister().read(NewsModel.class, reader, false);
            } catch (Exception e) {
                Log.i(LOG_TAG, "Error parse! " + rssUrl);
                throw new IOException("Error parse! " + rssUrl);
            }
        }
        finally {
            if (inputStream != null)
                inputStream.close();
            if (connection != null)
                connection.disconnect();
        }
        return newsModel;
    }

    @Override
    public Observable<NewsModel> getNewsObservable() {
        Observable<String> urlObservable = Observable.fromArray(IRssList.RSS_ARRAY);
        return urlObservable.subscribeOn(Schedulers.io())
                .map(this::getNews)
                .observeOn(AndroidSchedulers.mainThread());
    }
}