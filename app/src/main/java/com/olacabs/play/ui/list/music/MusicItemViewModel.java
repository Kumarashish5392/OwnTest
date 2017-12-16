package com.olacabs.play.ui.list.music;

import com.olacabs.play.repo.model.Music;

public class MusicItemViewModel {

    public Music mBlog;

    public BlogItemViewModelListener mListener;

    public MusicItemViewModel(Music blog, BlogItemViewModelListener listener) {
        this.mBlog = blog;
        this.mListener = listener;
    }

    public void onItemClick() {
        mListener.onItemClick(mBlog.getUrl());
    }

    public interface BlogItemViewModelListener {
        void onItemClick(String blogUrl);
    }

}
