package com.example.tancorik.gazetaruapp.dagger2.component;

import com.example.tancorik.gazetaruapp.dagger2.module.AppModule;
import com.example.tancorik.gazetaruapp.dagger2.module.PresenterModule;
import com.example.tancorik.gazetaruapp.presentation.presenter.MainScreenPresenter;
import com.example.tancorik.gazetaruapp.presentation.ui.NewsPageFragment;
import com.example.tancorik.gazetaruapp.presentation.ui.MainActivity;
import com.example.tancorik.gazetaruapp.presentation.ui.NetworkReceiver;
import com.example.tancorik.gazetaruapp.presentation.ui.NewsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, PresenterModule.class})
@Singleton
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(NewsFragment newsFragment);
    void inject(NewsPageFragment pageFragment);
    void inject(MainScreenPresenter presenter);
    void inject(NetworkReceiver receiver);
}
