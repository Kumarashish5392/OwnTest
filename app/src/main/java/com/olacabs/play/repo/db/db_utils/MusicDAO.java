package com.olacabs.play.repo.db.db_utils;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.olacabs.play.repo.model.Music;

import java.util.List;

@Dao
public interface MusicDAO {

    @Query("SELECT * FROM music")
    List<Music> loadAll();

    @Query("SELECT * FROM music WHERE id = :questionId")
    List<Music> loadAllByQuestionId(Long questionId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Music option);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Music> options);

}