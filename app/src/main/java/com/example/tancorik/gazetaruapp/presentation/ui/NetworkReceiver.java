package com.example.tancorik.gazetaruapp.presentation.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.tancorik.gazetaruapp.application.MyApp;
import com.example.tancorik.gazetaruapp.presentation.presenter.MainScreenPresenter;

import javax.inject.Inject;

public class NetworkReceiver extends BroadcastReceiver {

    @Inject
    MainScreenPresenter mPresenter;

    NetworkReceiver() {
        MyApp.getAppComponent().inject(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (mPresenter != null)
            mPresenter.updateState();
    }
}
