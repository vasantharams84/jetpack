package com.androrams.workshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androrams.workshop.R;
import com.androrams.workshop.adapter.CommentsAdapter;
import com.androrams.workshop.model.Comment;
import com.androrams.workshop.model.CommentsViewModel;
import com.androrams.workshop.model.VideoDetails;
import com.androrams.workshop.model.VideoViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoViewModel videoViewModel;
    private CommentsViewModel commentsViewModel;
    private CommentsAdapter adapter;
    private RecyclerView recyclerView;
    private TextView subscribeButton;
    private View channelBox;
    private ImageView likeButton;
    private ImageView imageView;
    private TextView channelName;
    private TextView desc;
    private TextView time;
    private TextView subs;
    private TextView views;

    private VideoDetails videoDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        views = findViewById(R.id.views);
        subs = findViewById(R.id.subscribers);
        time = findViewById(R.id.time);
        desc = findViewById(R.id.desc);
        channelName = findViewById(R.id.channelName);
        imageView = findViewById(R.id.videoImage);
        likeButton = findViewById(R.id.likes);
        channelBox = findViewById(R.id.channelBox);
        subscribeButton = findViewById(R.id.subscribeButton);


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

// event listeners
        likeButton.setOnClickListener(this);
        channelBox.setOnClickListener(this);
        subscribeButton.setOnClickListener(this);

        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);

        videoViewModel.getVideoDetails().observe(this, new Observer<VideoDetails>() {
            @Override
            public void onChanged(@Nullable VideoDetails data) {
                videoDetails = data;
                renderUI();
            }
        });

        commentsViewModel = ViewModelProviders.of(this).get(CommentsViewModel.class);

        commentsViewModel.getComments().observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                renderComments(comments);
            }
        });

        Button button = findViewById(R.id.sendButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comment comment = new Comment();
                EditText postCommentTextView = findViewById(R.id.commentBox);

                comment.setComment(postCommentTextView.getText().toString());
                comment.setUser("randomUser");
                commentsViewModel.postComment(comment);
                postCommentTextView.setText("");
            }
        });
    }

    private void renderComments(List<Comment> comments) {
        adapter = new CommentsAdapter(MainActivity.this, comments);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    private void renderUI() {

        Picasso.get().load(videoDetails.getVideoUrl()).into(imageView);
        views.setText(videoDetails.getViews());
        subs.setText(getString(R.string.subscribers_text,videoDetails.getSubscribers()));
        time.setText(videoDetails.getUploadTime());
        desc.setText(videoDetails.getDescription());
        channelName.setText(videoDetails.getChannelName());
        likeButton.setImageResource(videoDetails.isLiked() ? R.drawable.ic_like_fill : R.drawable.ic_like);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.subscribeButton:
                subscribeButton.setText("");
                subscribeButton.setBackgroundResource(R.drawable.ic_check_black_24dp);
                break;
            case R.id.channelBox:
                startActivity(new Intent(MainActivity.this, ChannelDetailsPage.class));
                break;
            case R.id.likes:
                videoDetails.setLiked(!videoDetails.isLiked());
                likeButton.setImageResource(videoDetails.isLiked() ? R.drawable.ic_like_fill : R.drawable.ic_like);
                break;
            default:
                break;

        }
    }
}
