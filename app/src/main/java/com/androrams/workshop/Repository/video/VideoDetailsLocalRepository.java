package com.androrams.workshop.Repository.video;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.androrams.workshop.database.AppDatabase;
import com.androrams.workshop.database.VideoDao;
import com.androrams.workshop.model.VideoDetails;

public class VideoDetailsLocalRepository  extends VideoDetailsRepository{
    private static VideoDetailsLocalRepository newsRepository;
    private static MutableLiveData<VideoDetails> videoDetails;
    private final VideoDao dao;

    public VideoDetailsLocalRepository(Context context) {
        videoDetails = new MutableLiveData<>();
        dao = AppDatabase.getInstance(context).videoDao();

    }

    public MutableLiveData<VideoDetails> getVideoDetails() {
        dao.getAll();
        return videoDetails;
    }
}
