package com.androrams.workshop.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androrams.workshop.Repository.video.VideoDetailsRemoteRepository;
import com.androrams.workshop.Repository.video.VideoDetailsRepository;
import com.androrams.workshop.Utils;

public class VideoViewModel extends AndroidViewModel {


    @Nullable
    private MutableLiveData<VideoDetails> videoDetails;

    public VideoViewModel(@NonNull Application application) {
        super(application);
    }

    @Nullable
    public MutableLiveData<VideoDetails> getVideoDetails() {
        if (videoDetails == null) {
            videoDetails = new MutableLiveData<VideoDetails>();
            VideoDetailsRepository repository = VideoDetailsRepository.getInstance(this.getApplication());
            videoDetails = repository.getVideoDetails();
        }
        return videoDetails;
    }

    public void storeInDB(VideoDetails videoDetails){
        VideoDetailsRepository repository = new VideoDetailsRemoteRepository();
    }
}