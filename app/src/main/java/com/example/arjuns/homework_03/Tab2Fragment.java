package com.example.arjuns.homework_03;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by arjuns on 7/2/2015.
 */
public class Tab2Fragment extends Fragment {
    private static View view;
    private static final int REQUEST_CODE_IMAGE = 3;
    ImageView myPhotoView;
    BitmapFactory.Options myBitmapOptions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.camera_layout, container, false);
        Button myCaptureImageButton = (Button) view.findViewById(R.id.capturePhotoButton);
        Button myCaptureVideoButton = (Button) view.findViewById(R.id.captureVideoButton);
        myPhotoView = (ImageView) view.findViewById(R.id.photoView);
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
                startActivity(invokeCameraIntent2);
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
                    String filePath = data.getStringExtra("imageFilePath");
                    //videoPreview.setVisibility(View.GONE);

                    myPhotoView.setVisibility(View.VISIBLE);
                    myBitmapOptions = new BitmapFactory.Options();
                    myBitmapOptions.inSampleSize = 2;
                    Bitmap myBitmap = BitmapFactory.decodeFile(filePath,myBitmapOptions);
                    myPhotoView.setImageBitmap(myBitmap);
                }
        }
    }
}
