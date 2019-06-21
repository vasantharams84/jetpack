package com.androrams.workshop.model;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androrams.workshop.Repository.CommentsRepository;

import java.util.List;

public class CommentsViewModel extends ViewModel {

    @Nullable
    private MutableLiveData<List<Comment>> comments;

    @Nullable
    public MutableLiveData<List<Comment>> getComments() {
        CommentsRepository repository = CommentsRepository.getInstance();
        comments = repository.getComments();
        return comments;
    }

    @Nullable
    public void postComment(Comment comment){
        CommentsRepository repository = CommentsRepository.getInstance();
        comments = repository.postComment(comment);
    }
}
