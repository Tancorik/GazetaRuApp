package com.example.tancorik.gazetaruapp.presentation.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tancorik.gazetaruapp.R;
import com.example.tancorik.gazetaruapp.application.MyApp;
import com.example.tancorik.gazetaruapp.presentation.presenter.MainScreenPresenter;

import javax.inject.Inject;

public class NewsFragment extends Fragment {

    @Inject
    MainScreenPresenter mPresenter;
    private ViewPager mViewPager;
    private NewsPagerAdapter mPagerAdapter;
//    private int mKoefPage;

    public static final String TAG = "news_fragment";

    public static NewsFragment getInstance(FragmentManager fm) {
        NewsFragment newsFragment = (NewsFragment) fm.findFragmentByTag(NewsFragment.TAG);
        if (newsFragment == null) {
            newsFragment = new NewsFragment();
        }
        return newsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp.getAppComponent().inject(this);
//        int countPage = mPresenter.getAllNews().size();
//        mKoefPage = Integer.MAX_VALUE/countPage/2*countPage;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mViewPager = view.findViewById(R.id.view_pager);
        mPagerAdapter = new NewsPagerAdapter(getFragmentManager());
        mPagerAdapter.setNewsList(mPresenter.getAllNews());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(1);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0){
                    int count = mPagerAdapter.getCount();
                    if (position == 0) {
                        mViewPager.setCurrentItem(count - 2, false);
                    }
                    else if (position == count-1) {
                        mViewPager.setCurrentItem(1, false);
//                    Toast.makeText(getContext(), "position" + position, Toast.LENGTH_SHORT).show();
                    }
                }
//                Toast.makeText(getContext(), "position - " + position + "offset - " + positionOffset, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mViewPager = view.findViewById(R.id.view_pager);
//        mPagerAdapter = new NewsPagerAdapter(getFragmentManager());
//        mPagerAdapter.setNewsList(mPresenter.getAllNews());
//        mViewPager.setAdapter(mPagerAdapter);
    }

    public void setCurrentPage(int page) {
        if (mViewPager != null)
            mViewPager.setCurrentItem(page+1, true);
//            mViewPager.setCurrentItem(mKoefPage+page,true);
    }

}
