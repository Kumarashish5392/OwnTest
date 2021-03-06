package com.olacabs.play.repo.db;

import com.olacabs.play.repo.model.Music;

import java.util.List;

import io.reactivex.Observable;

/**
 * Generated by Dipendra on 06-11-2017 - 10:56 AM
 */


public interface IDBHelper {

    Observable<List<Music>> getAllMusic();

    Observable<Boolean> isMusicEmpty();

    boolean saveMusic(Music music);

    boolean saveMusicList(List<Music> musicList);

}
