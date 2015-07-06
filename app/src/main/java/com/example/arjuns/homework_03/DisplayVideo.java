package com.example.arjuns.homework_03;

import android.app.Activity;
import android.content.Intent;
import android.widget.MediaController;
import android.os.Bundle;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.view.View;
import android.widget.VideoView;

/**
 * Created by arjuns on 7/6/2015.
 */
public class DisplayVideo extends Activity {
    VideoView myVideoView;
    DisplayMetrics myDisplayMetrics;
    SurfaceView mySurfaceView;
    MediaController myMediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_video_layout);
        VideoView myDisplayVideoView = (VideoView)findViewById(R.id.myGridVideoView);
        Intent displayMediaIntent = getIntent();
        String path = displayMediaIntent.getStringExtra("filePath");
        myMediaController = new MediaController(this);
        //Displaying all the metrics
        myDisplayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(myDisplayMetrics);
        int height = myDisplayMetrics.heightPixels;
        int width = myDisplayMetrics.widthPixels;

        myDisplayVideoView.setMinimumWidth(width);
        myDisplayVideoView.setMinimumHeight(height);
        myDisplayVideoView.setVisibility(View.VISIBLE);
        myDisplayVideoView.setMediaController(myMediaController);
        myDisplayVideoView.setVideoPath(path);
        myDisplayVideoView.requestFocus();

        myDisplayVideoView.start();

    }
}