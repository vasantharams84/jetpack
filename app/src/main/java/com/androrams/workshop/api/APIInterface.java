package com.androrams.workshop.api;

import com.androrams.workshop.model.Comment;
import com.androrams.workshop.model.VideoDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @GET("video")
    Call<VideoDetails> getVideoDetails();

    @GET("comments")
    Call<List<Comment>> getComments();

    @POST("comment")
    Call<List<Comment>> postComment(@Body Comment comment);
}
