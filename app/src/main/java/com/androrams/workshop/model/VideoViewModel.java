package com.androrams.workshop.model;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androrams.workshop.api.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoViewModel extends ViewModel {

    @Nullable
    private MutableLiveData<VideoDetails> videoDetails;

    @Nullable
    public MutableLiveData<VideoDetails> getVideoDetails() {
        if (videoDetails == null) {
            videoDetails = new MutableLiveData<VideoDetails>();
            loadVideoDetails();
        }
        return videoDetails;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadVideoDetails() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface api = retrofit.create(APIInterface.class);
        Call<VideoDetails> call = api.getVideoDetails();


        call.enqueue(new Callback<VideoDetails>() {
            @Override
            public void onResponse(Call<VideoDetails> call, Response<VideoDetails> response) {
                if (videoDetails != null) {
                    videoDetails.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<VideoDetails> call, Throwable t) {
                Log.e("Test","Test");

            }


        });
    }
}