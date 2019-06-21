package com.androrams.workshop.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.androrams.workshop.api.APIInterface;
import com.androrams.workshop.api.RetrofitService;
import com.androrams.workshop.model.VideoDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoDetailsRepository {
    private static VideoDetailsRepository newsRepository;
    private APIInterface apiInterface;

    public static VideoDetailsRepository getInstance() {
        if (newsRepository == null) {
            newsRepository = new VideoDetailsRepository();
        }
        return newsRepository;
    }


    private VideoDetailsRepository() {
        apiInterface = RetrofitService.cteateService(APIInterface.class);
    }


    public MutableLiveData<VideoDetails> getVideoDetails() {

        Call<VideoDetails> call = apiInterface.getVideoDetails();
        final MutableLiveData<VideoDetails> videoDetails = new MutableLiveData<>();

        call.enqueue(new Callback<VideoDetails>() {
            @Override
            public void onResponse(Call<VideoDetails> call, Response<VideoDetails> response) {
                videoDetails.setValue(response.body());
            }

            @Override
            public void onFailure(Call<VideoDetails> call, Throwable t) {
                Log.e("Test", "Test");
            }
        });
        return videoDetails;
    }
}