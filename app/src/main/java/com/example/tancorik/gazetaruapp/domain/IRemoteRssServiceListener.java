package com.example.tancorik.gazetaruapp.domain;

import com.example.tancorik.gazetaruapp.presentation.model.NewsModel;

public interface IRemoteRssServiceListener {
    void onSuccess(NewsModel newsModel);
    void onError(Throwable error);
}
