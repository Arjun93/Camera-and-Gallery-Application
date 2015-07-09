package com.example.arjuns.homework_03;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
    Button myCaptureImageButton, myCaptureVideoButton, mySaveButton, myDiscardButton;
    File myMediaFile;
    String filePath1,filePath2;
    boolean isImageFlag = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.camera_layout, container, false);
        myCaptureImageButton = (Button) view.findViewById(R.id.capturePhotoButton);
        myCaptureVideoButton = (Button) view.findViewById(R.id.captureVideoButton);
        mySaveButton = (Button) view.findViewById(R.id.saveButton);
        myDiscardButton = (Button) view.findViewById(R.id.discardButton);
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

        myDiscardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMediaFile.delete();
                Toast.makeText(getActivity().getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                myVideoView.setVisibility(View.INVISIBLE);
                myPhotoView.setVisibility(View.INVISIBLE);
            }
        });

        mySaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isImageFlag) {
                    String fileName = "IMAGE_" + Long.toString(System.currentTimeMillis()) + ".jpg";
                    myMediaFile = new File(filePath1, fileName);
                    ContentValues values = new ContentValues();

                    values.put(MediaStore.Files.FileColumns.DATE_ADDED, System.currentTimeMillis());
                    values.put(MediaStore.Files.FileColumns.DATE_MODIFIED, System.currentTimeMillis());
                    values.put(MediaStore.Files.FileColumns.MEDIA_TYPE, MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE);
                    values.put(MediaStore.Files.FileColumns.DATA, filePath1);
                    getActivity().getContentResolver().insert(MediaStore.Files.getContentUri("external"), values);
                }
                else {
                    String currentTime = Long.toString(System.currentTimeMillis());
                    String fileName = "MOV"+currentTime+".mp4";
                    myMediaFile = new File(filePath2, fileName);
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Files.FileColumns.DATE_ADDED, currentTime);
                    values.put(MediaStore.Files.FileColumns.DATE_MODIFIED, currentTime);
                    values.put(MediaStore.Files.FileColumns.MEDIA_TYPE, MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO);
                    values.put(MediaStore.Files.FileColumns.DATA, filePath2);
                    getActivity().getContentResolver().insert(MediaStore.Files.getContentUri("external"), values);
                }
                Toast.makeText(getActivity().getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
                myVideoView.setVisibility(View.INVISIBLE);
                myPhotoView.setVisibility(View.INVISIBLE);
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
                    isImageFlag = true;
                    filePath1 = data.getStringExtra("imageFilePath");
                    myVideoView.setVisibility(View.INVISIBLE);
                    myPhotoView.setVisibility(View.VISIBLE);
                    myBitmapOptions = new BitmapFactory.Options();
                    myBitmapOptions.inSampleSize = 2;
                    Bitmap myBitmap = BitmapFactory.decodeFile(filePath1,myBitmapOptions);
                    myPhotoView.setImageBitmap(myBitmap);
                    myMediaFile = new File(filePath1);
                    //myImageFile.delete();
                }
                break;

            case (REQUEST_CODE_VIDEO):
                if (resultCode == Activity.RESULT_OK) {
                    isImageFlag = false;
                    filePath2 = data.getStringExtra("videoFilePath");
                    myPhotoView.setVisibility(View.INVISIBLE);
                    myVideoView.setVisibility(View.VISIBLE);
                    Log.i("FILEPATH", "" + filePath2);
                    myVideoView.setVideoPath(filePath2);
                    myVideoView.requestFocus();
                    myVideoView.start();
                    myMediaFile = new File(filePath2);
                    //myVideoFile.delete();
                }
                break;
        }
    }
}
