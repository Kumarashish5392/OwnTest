package com.olacabs.play.ui.list.music;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.util.Log;

import com.olacabs.play.repo.IDataManager;
import com.olacabs.play.repo.model.Music;
import com.olacabs.play.ui.base.BaseViewModel;
import com.olacabs.play.utils.rx.ISchedulerProvider;

import java.util.List;


public class MusicViewModel extends BaseViewModel<MusicNavigator> {

    private final ObservableArrayList<Music> musicObservableArrayList
            = new ObservableArrayList<>();
    private final MutableLiveData<List<Music>> blogListLiveData;

    public MusicViewModel(IDataManager dataManager,
                          ISchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        blogListLiveData = new MutableLiveData<>();
        fetchMusic();
    }

    public void fetchMusic() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllMusic()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(blogResponse -> {
                    if (blogResponse != null) {
                        Log.e("Music", blogResponse.toString());
                        blogListLiveData.setValue(blogResponse);
                        getNavigator().updateBlog(blogResponse);
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    Log.e("Music", throwable.getMessage());
                    getNavigator().handleError(throwable);
                }));
    }

    public MutableLiveData<List<Music>> getBlogListLiveData() {
        return blogListLiveData;
    }

    public void addMusicItemsToList(List<Music> blogs) {
        musicObservableArrayList.clear();
        musicObservableArrayList.addAll(blogs);
    }

    public ObservableArrayList<Music> getMusicObservableArrayList() {
        return musicObservableArrayList;
    }



}
