package com.androrams.workshop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.androrams.workshop.R;
import com.androrams.workshop.model.VideoDetails;
import com.androrams.workshop.model.VideoViewModel;

public class ChannelDetailsPage extends AppCompatActivity {

    private VideoViewModel videoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        // The ViewModelStore provides a new ViewModel or one previously created.

        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
        videoViewModel.getVideoDetails().observe(this, new Observer<VideoDetails>() {
            @Override
            public void onChanged(@Nullable VideoDetails videoDetails) {
                renderUI(videoDetails);
            }
        });
    }

    private void renderUI(VideoDetails videoDetails) {
        TextView channelName = findViewById(R.id.channelName);
        TextView description = findViewById(R.id.description);
        TextView subs = findViewById(R.id.subscribers);

        if (videoDetails != null) {
            channelName.setText(videoDetails.getChannelName());
            description.setText(videoDetails.getDescription());
            subs.setText(videoDetails.getSubscribers());
        }


    }
}
