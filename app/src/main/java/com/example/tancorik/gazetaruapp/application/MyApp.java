package com.example.tancorik.gazetaruapp.application;

import android.app.Application;

import com.example.tancorik.gazetaruapp.dagger2.component.AppComponent;
import com.example.tancorik.gazetaruapp.dagger2.component.DaggerAppComponent;
import com.example.tancorik.gazetaruapp.dagger2.module.AppModule;

public class MyApp extends Application {

    private static AppComponent sAppComponent;

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAppComponent = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
