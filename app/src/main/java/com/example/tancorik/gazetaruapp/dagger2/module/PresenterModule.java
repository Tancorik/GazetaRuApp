package com.example.tancorik.gazetaruapp.dagger2.module;

import android.support.annotation.NonNull;

import com.example.tancorik.gazetaruapp.presentation.presenter.MainScreenPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @NonNull
    @Singleton
    MainScreenPresenter provideMainScreenPresenter() {
        return new MainScreenPresenter();
    }
}
