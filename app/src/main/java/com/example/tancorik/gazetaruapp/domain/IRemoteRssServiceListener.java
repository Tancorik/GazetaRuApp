package com.example.tancorik.gazetaruapp.domain;

import com.example.tancorik.gazetaruapp.presentation.model.News;

public interface IRemoteRssServiceListener {
    void onSuccess(News news);
    void onError(Throwable error);
}
