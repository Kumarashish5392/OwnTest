package com.olacabs.play.ui.list.music;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.olacabs.play.ViewModelProviderFactory;
import com.olacabs.play.repo.IDataManager;
import com.olacabs.play.utils.rx.ISchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class MusicFragmentModule {

    @Provides
    MusicViewModel blogViewModel(IDataManager dataManager,
                                 ISchedulerProvider schedulerProvider) {
        return new MusicViewModel(dataManager, schedulerProvider);
    }

    @Provides
    MusicListAdapter provideBlogAdapter() {
        return new MusicListAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(MusicFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    ViewModelProvider.Factory provideBlogViewModel(MusicViewModel blogViewModel) {
        return new ViewModelProviderFactory<>(blogViewModel);
    }

}
