package com.example.tancorik.gazetaruapp.presentation.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tancorik.gazetaruapp.R;

public class ProgressFragment extends Fragment {

    public static final String TAG = "progress_fragment";

    private TextView mDownloadItemView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.progress_fragment, container, false);
        mDownloadItemView = view.findViewById(R.id.download_item_text);
        return view;
    }

    public void setDownloadItemText(String text) {
        if (mDownloadItemView!=null)
            mDownloadItemView.setText(text);
    }
}
