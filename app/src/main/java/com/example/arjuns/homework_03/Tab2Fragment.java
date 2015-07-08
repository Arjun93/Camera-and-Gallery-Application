package com.example.arjuns.homework_03;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by arjuns on 7/2/2015.
 */
public class Tab2Fragment extends Fragment {
    private static View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.camera_layout, container, false);
        Button myCaptureImageButton = (Button)view.findViewById(R.id.capturePhotoButton);
        Button myCaptureVideoButton = (Button)view.findViewById(R.id.captureVideoButton);

        myCaptureImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent invokeCameraIntent1 = new Intent(getActivity().getApplicationContext(),TakePictureActivity.class);
                startActivity(invokeCameraIntent1);
            }
        });
        myCaptureVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent invokeCameraIntent2 = new Intent(getActivity().getApplicationContext(),TakeVideoActivity.class);
                startActivity(invokeCameraIntent2);
            }
        });


        return view;
    }
}
