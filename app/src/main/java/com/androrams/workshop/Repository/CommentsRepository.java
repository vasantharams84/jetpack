package com.androrams.workshop.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.androrams.workshop.api.APIInterface;
import com.androrams.workshop.api.RetrofitService;
import com.androrams.workshop.model.Comment;
import com.androrams.workshop.model.VideoDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsRepository {

    private static CommentsRepository newsRepository;
    private APIInterface apiInterface;
    private static MutableLiveData<List<Comment>> comments;



    public static CommentsRepository getInstance() {
        if (newsRepository == null) {
            newsRepository = new CommentsRepository();
        }
        return newsRepository;
    }

    private CommentsRepository() {
        apiInterface = RetrofitService.cteateService(APIInterface.class);
        comments = new MutableLiveData<>();
    }


    public MutableLiveData<List<Comment>> postComment(Comment comment) {

        Call<List<Comment>> call = apiInterface.postComment(comment);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                comments.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.e("Test", "Test");
            }
        });

        return comments;

    }

    //This method is using Retrofit to get the JSON data from URL
    public MutableLiveData<List<Comment>> getComments() {
        Call<List<Comment>> call = apiInterface.getComments();


        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                comments.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.e("Test", "Test");
            }


        });
        return comments;
    }
}