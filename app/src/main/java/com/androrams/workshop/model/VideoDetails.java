package com.androrams.workshop.model;

import com.google.gson.annotations.SerializedName;

public class VideoDetails {

    @SerializedName("image")
    private String videoUrl;

    @SerializedName("uploadedTimeline")
    private String uploadTime;
    @SerializedName("views")
    private String views;
    private String likes;

    @SerializedName("description")
    private String description;
    @SerializedName("channel")
    private String channelName;
    @SerializedName("channelSubscribers")
    private String subscribers;
    private boolean isLiked;

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(String subscribers) {
        this.subscribers = subscribers;
    }
}
