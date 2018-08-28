package com.example.tancorik.gazetaruapp.presentation.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tancorik.gazetaruapp.R;
import com.example.tancorik.gazetaruapp.presentation.model.News;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {

    private News mNews;
    private INewsClickListener mListener;

    NewsRecyclerAdapter(News news, INewsClickListener listener) {
        mNews = news;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.news_item_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTitleTextView.setText(mNews.getChannel().getItemList().get(position).getTitle());
        holder.mDateTextView.setText(mNews.getChannel().getItemList().get(position).getPubDate());
        holder.mDescriptionTextView.setText(mNews.getChannel().getItemList().get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mNews.getChannel().getItemList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleTextView;
        TextView mDescriptionTextView;
        TextView mDateTextView;

        ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.news_title);
            mDateTextView = itemView.findViewById(R.id.news_date);
            mDescriptionTextView = itemView.findViewById(R.id.news_description);
            itemView.setOnClickListener((view)-> mListener.onNewsClick(getAdapterPosition()));
        }
    }

    interface INewsClickListener {
        void onNewsClick(int position);
    }
}
