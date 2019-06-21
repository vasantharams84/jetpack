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
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androrams.workshop.adapter.CommentsAdapter;
import com.androrams.workshop.R;
import com.androrams.workshop.model.Comment;
import com.androrams.workshop.model.CommentsViewModel;
import com.androrams.workshop.model.VideoDetails;
import com.androrams.workshop.model.VideoViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    VideoViewModel videoViewModel;
    CommentsViewModel commentsViewModel;
    private CommentsAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         recyclerView = findViewById(R.id.recyclerview);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         recyclerView.setHasFixedSize(true);

        // The ViewModelStore provides a new ViewModel or one previously created.

        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);

        videoViewModel.getVideoDetails().observe(this, new Observer<VideoDetails>() {
            @Override
            public void onChanged(@Nullable VideoDetails videoDetails) {
                 renderUI(videoDetails);
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


    private void renderUI(final VideoDetails videoDetails) {

        TextView views = findViewById(R.id.views);
        TextView subs = findViewById(R.id.subscribers);
        TextView time = findViewById(R.id.time);
        TextView desc = findViewById(R.id.desc);
        TextView channelName = findViewById(R.id.channelName);
        ImageView imageView = findViewById(R.id.videoImage);
        final ImageView likes = findViewById(R.id.likes);

        Picasso.get().load(videoDetails.getVideoUrl()).into(imageView);

        views.setText(videoDetails.getViews());
        subs.setText(videoDetails.getSubscribers());
        time.setText(videoDetails.getUploadTime());
        desc.setText(videoDetails.getDescription());
        channelName.setText(videoDetails.getChannelName());
        likes.setImageResource(videoDetails.isLiked() ? R.drawable.ic_like_fill : R.drawable.ic_like);

        findViewById(R.id.likes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoDetails.setLiked(!videoDetails.isLiked());
                likes.setImageResource(videoDetails.isLiked() ? R.drawable.ic_like_fill : R.drawable.ic_like);
            }
        });

        findViewById(R.id.channelBox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ChannelDetailsPage.class));
            }
        });

        findViewById(R.id.subscribeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Button) findViewById(R.id.subscribeButton)).setText("");
                ((Button) findViewById(R.id.subscribeButton)).setBackgroundResource(R.drawable.ic_check_black_24dp);
            }
        });
    }

}
