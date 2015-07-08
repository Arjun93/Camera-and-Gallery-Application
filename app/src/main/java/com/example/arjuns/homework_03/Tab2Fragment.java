package com.example.arjuns.homework_03;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * Created by arjuns on 7/2/2015.
 */
public class Tab2Fragment extends Fragment {
    private static View view;
    private static final int REQUEST_CODE_IMAGE = 3;
    private static final int REQUEST_CODE_VIDEO = 4;
    ImageView myPhotoView;
    VideoView myVideoView;
    BitmapFactory.Options myBitmapOptions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.camera_layout, container, false);
        Button myCaptureImageButton = (Button) view.findViewById(R.id.capturePhotoButton);
        Button myCaptureVideoButton = (Button) view.findViewById(R.id.captureVideoButton);
        myPhotoView = (ImageView) view.findViewById(R.id.photoView);
        myVideoView = (VideoView) view.findViewById(R.id.videoView);
        myCaptureImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent invokeCameraIntent1 = new Intent(getActivity().getApplicationContext(), TakePictureActivity.class);
                startActivityForResult(invokeCameraIntent1, REQUEST_CODE_IMAGE);

            }
        });
        myCaptureVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent invokeCameraIntent2 = new Intent(getActivity().getApplicationContext(), TakeVideoActivity.class);
                startActivityForResult(invokeCameraIntent2, REQUEST_CODE_VIDEO);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (REQUEST_CODE_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    String filePath1 = data.getStringExtra("imageFilePath");
                    myVideoView.setVisibility(View.INVISIBLE);
                    myPhotoView.setVisibility(View.VISIBLE);
                    myBitmapOptions = new BitmapFactory.Options();
                    myBitmapOptions.inSampleSize = 2;
                    Bitmap myBitmap = BitmapFactory.decodeFile(filePath1,myBitmapOptions);
                    myPhotoView.setImageBitmap(myBitmap);
                }
                break;

            case (REQUEST_CODE_VIDEO):
                if (resultCode == Activity.RESULT_OK) {
                    String filePath2 = data.getStringExtra("videoFilePath");
                    myPhotoView.setVisibility(View.INVISIBLE);
                    myVideoView.setVisibility(View.VISIBLE);
                    Log.i("FILEPATH", "" + filePath2);
                    myVideoView.setVideoPath(filePath2);
                    myVideoView.requestFocus();
                    myVideoView.start();
                }
                break;
        }
    }
}
