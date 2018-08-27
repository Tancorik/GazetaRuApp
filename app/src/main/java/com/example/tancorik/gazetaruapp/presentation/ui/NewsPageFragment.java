package com.example.tancorik.gazetaruapp.presentation.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tancorik.gazetaruapp.R;
import com.example.tancorik.gazetaruapp.application.MyApp;
import com.example.tancorik.gazetaruapp.presentation.model.News;
import com.example.tancorik.gazetaruapp.presentation.presenter.MainScreenPresenter;

import java.util.List;

import javax.inject.Inject;

public class NewsPageFragment extends Fragment implements NewsRecyclerAdapter.NewsClickListener{


    @Inject
    MainScreenPresenter mPresenter;
    private static final String ARG_KEY = "position_key";
    private int mPosition;
    private NewsRecyclerAdapter mRecyclerAdapter;

    public static NewsPageFragment newInstance(int position) {
        NewsPageFragment fragment = new NewsPageFragment();
        Bundle argument = new Bundle();
        argument.putInt(ARG_KEY, position);
        fragment.setArguments(argument);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApp.getAppComponent().inject(this);
        Bundle arg = getArguments();
        if (arg != null){
            mPosition = arg.getInt(ARG_KEY);
        }

        mRecyclerAdapter = new NewsRecyclerAdapter(mPresenter.getNews(mPosition), this);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_item_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
//        recyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = recyclerView.getChildLayoutPosition(view);
//                Toast.makeText(getContext(), "Позиция" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
        recyclerView.setAdapter(mRecyclerAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onNewsClick(int position) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(mPresenter.getNews(mPosition).getChannel().getItemList().get(position).getLink()));
        startActivity(intent);
    }
}
