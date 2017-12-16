package com.olacabs.play.ui.list.music;

public class MusicEmptyItemViewModel {

    private MusicEmptyItemViewModelListener mListener;

    public MusicEmptyItemViewModel(MusicEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface MusicEmptyItemViewModelListener {
        void onRetryClick();
    }

}
