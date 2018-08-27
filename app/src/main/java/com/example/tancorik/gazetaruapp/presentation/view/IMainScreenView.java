package com.example.tancorik.gazetaruapp.presentation.view;

import com.example.tancorik.gazetaruapp.presentation.model.News;

import java.util.List;

public interface IMainScreenView {
    void showProgress();
    void showNews(List<News> newsList);
    void updateDownloadText(String text);
    void showError();
}
