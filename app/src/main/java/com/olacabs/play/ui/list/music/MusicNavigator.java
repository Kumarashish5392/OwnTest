package com.olacabs.play.ui.list.music;

import com.olacabs.play.repo.model.Music;

import java.util.List;

public interface MusicNavigator {

    void updateBlog(List<Music> blogList);

    void handleError(Throwable throwable);

}
