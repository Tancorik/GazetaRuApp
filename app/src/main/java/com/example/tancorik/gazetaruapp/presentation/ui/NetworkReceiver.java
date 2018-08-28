package com.example.tancorik.gazetaruapp.presentation.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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
        Log.i("Log_TAG_PRESENTER", "Ресивер принял сообщение");
        if (mPresenter != null)
            mPresenter.updateState();
    }
}
