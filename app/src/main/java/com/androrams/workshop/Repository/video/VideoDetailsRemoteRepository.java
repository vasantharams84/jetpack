package com.androrams.workshop.Repository.video;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.androrams.workshop.api.APIInterface;
import com.androrams.workshop.api.RetrofitService;
import com.androrams.workshop.model.VideoDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoDetailsRemoteRepository extends VideoDetailsRepository {
    private APIInterface apiInterface;
    private static MutableLiveData<VideoDetails> videoDetails;


    public VideoDetailsRemoteRepository() {
        apiInterface = RetrofitService.cteateService(APIInterface.class);
        videoDetails = new MutableLiveData<>();
    }

    public MutableLiveData<VideoDetails> getVideoDetails() {

        Call<VideoDetails> call = apiInterface.getVideoDetails();

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
