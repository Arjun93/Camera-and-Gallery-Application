package com.example.arjuns.homework_03;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by arjuns on 7/6/2015.
 */
public class CameraActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_layout);
        /*if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.tab2, Camera2BasicFragment.newInstance())
                    .commit();
        }*/
    }
}

