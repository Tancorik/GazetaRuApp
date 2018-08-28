package com.example.tancorik.gazetaruapp.presentation.ui;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tancorik.gazetaruapp.presentation.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsPagerAdapter extends FragmentStatePagerAdapter {

    private List<News> mNewsList = new ArrayList<>();

    NewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setNewsList(List<News> newsList) {
        mNewsList.clear();
        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return NewsPageFragment.newInstance(mNewsList.size()-1);
        if (position == mNewsList.size()+1)
            return NewsPageFragment.newInstance(0);
        return NewsPageFragment.newInstance(position-1);
    }

    @Override
    public int getCount() {
        return mNewsList.size()+2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0)
            return mNewsList.get(mNewsList.size()-1).getChannel().getTitle();
        if (position == mNewsList.size()+1)
            return mNewsList.get(0).getChannel().getTitle();
        return mNewsList.get(position-1).getChannel().getTitle();
    }
}
