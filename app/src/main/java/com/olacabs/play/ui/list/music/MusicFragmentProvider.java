package com.olacabs.play.ui.list.music;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MusicFragmentProvider {

    @ContributesAndroidInjector(modules = MusicFragmentModule.class)
    abstract MusicFragment provideMusicFragmentFactory();

}
