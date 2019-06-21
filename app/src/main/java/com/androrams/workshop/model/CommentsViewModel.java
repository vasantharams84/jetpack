package com.androrams.workshop.model;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androrams.workshop.api.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentsViewModel extends ViewModel {

    @Nullable
    private MutableLiveData<List<Comment>> comments;

    @Nullable
    public MutableLiveData<List<Comment>> getComments() {
        if (comments == null) {
            comments = new MutableLiveData<List<Comment>>();
            loadComments();
        }
        return comments;
    }


    public void postComment(Comment comment){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface api = retrofit.create(APIInterface.class);
        Call<List<Comment>> call = api.postComment(comment);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (comments != null) {
                    comments.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.e("Test","Test");
            }


        });

    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadComments() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface api = retrofit.create(APIInterface.class);
        Call<List<Comment>> call = api.getComments();


        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (comments != null) {
                    comments.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.e("Test","Test");
            }


        });
    }
}
