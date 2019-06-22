package com.androrams.workshop.Repository.video;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.androrams.workshop.Utils;
import com.androrams.workshop.model.VideoDetails;

public abstract class VideoDetailsRepository {
    private static MutableLiveData<VideoDetails> videoDetails;

    public static VideoDetailsRepository getInstance(Context context) {

        boolean isConnected = Utils.isConnected(context);
        VideoDetailsRepository instance;
        if (isConnected) {
            instance = new VideoDetailsRemoteRepository();
        } else {
            instance = new VideoDetailsLocalRepository(context);
        }
        return instance;
    }

    public abstract MutableLiveData<VideoDetails> getVideoDetails();
}