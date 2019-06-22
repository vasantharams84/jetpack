package com.androrams.workshop.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.androrams.workshop.model.VideoDetails;

import java.util.List;

@Dao
public interface VideoDao {
    @Query("SELECT * FROM VideoDetails")
    LiveData<List<VideoDetails>> getAll();

    @Insert
    void insertAll(VideoDetails... videoDetails);
}
