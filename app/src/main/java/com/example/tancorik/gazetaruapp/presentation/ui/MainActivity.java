package com.example.tancorik.gazetaruapp.presentation.ui;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tancorik.gazetaruapp.R;
import com.example.tancorik.gazetaruapp.application.MyApp;
import com.example.tancorik.gazetaruapp.presentation.model.News;
import com.example.tancorik.gazetaruapp.presentation.presenter.MainScreenPresenter;
import com.example.tancorik.gazetaruapp.presentation.view.IMainScreenView;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IMainScreenView{


    @Inject
    MainScreenPresenter mPresenter;

    private ProgressFragment mProgressFragment;
    private NewsFragment mNewsFragment;
    private NetworkReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyApp.getAppComponent().inject(this);

        mProgressFragment = new ProgressFragment();
        mNewsFragment = NewsFragment.getInstance(getSupportFragmentManager());

//        if (savedInstanceState == null)
//        mPresenter.initPresenter(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.initPresenter(this);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        mReceiver = new NetworkReceiver();
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing())
            mPresenter.clearPresenter();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mReceiver);
    }

    @Override
    public void showProgress() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, mProgressFragment, ProgressFragment.TAG)
                .commit();
    }

    @Override
    public void showNews(List<News> newsList) {
        if (isChangingConfigurations()) {
            return;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, mNewsFragment, NewsFragment.TAG)
                .commitAllowingStateLoss();
        invalidateOptionsMenu();
    }

    @Override
    public void updateDownloadText(String text) {
        if (mProgressFragment.isVisible()) {
            mProgressFragment.setDownloadItemText(text);
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        List<News> newsList = mPresenter.getAllNews();
        menu.clear();
        for (int i = 0; i < newsList.size(); i++) {
            menu.add(0, i, 0, newsList.get(i).getChannel().getTitle());
        }
        return super.onPrepareOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mNewsFragment.setCurrentPage(item.getItemId());
        return super.onOptionsItemSelected(item);
    }
}
