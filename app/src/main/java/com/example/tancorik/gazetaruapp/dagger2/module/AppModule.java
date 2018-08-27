package com.example.tancorik.gazetaruapp.dagger2.module;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context mAppContext;

    public AppModule(@NonNull Context context) {
        mAppContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return  mAppContext;
    }
}
