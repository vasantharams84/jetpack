package com.androrams.workshop.model;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androrams.workshop.Repository.VideoDetailsRepository;

public class VideoViewModel extends ViewModel {

    @Nullable
    private MutableLiveData<VideoDetails> videoDetails;

    @Nullable
    public MutableLiveData<VideoDetails> getVideoDetails() {
        if (videoDetails == null) {
            videoDetails = new MutableLiveData<VideoDetails>();
            VideoDetailsRepository repository = VideoDetailsRepository.getInstance();
            videoDetails = repository.getVideoDetails();
        }
        return videoDetails;
    }
}