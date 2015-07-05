package com.example.arjuns.homework_03;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by arjuns on 7/2/2015.
 */
public class Tab2Fragment extends Fragment {
    private static View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.camera_layout, container, false);
        return view;
    }
}
