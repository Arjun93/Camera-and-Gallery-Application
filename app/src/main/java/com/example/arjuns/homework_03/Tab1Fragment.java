package com.example.arjuns.homework_03;

import android.app.Fragment;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by arjuns on 7/2/2015.
 */
public class Tab1Fragment extends Fragment {
    /**
     * Cursor used to access the results from querying for images on the SD card.
     */
    private Cursor cursor;
    /*
     * Column index for the Thumbnails Image IDs.
     */
    private int columnIndex;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View myView = inflater.inflate(R.layout.gallery_layout,container,false);
        GridView myGridView = (GridView)myView.findViewById(R.id.tab1);


        // Set up an array of the Thumbnail Image ID column we want
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};


        // Create the cursor pointing to the SDCard
        cursor = getActivity().managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection, // Which columns to return
                null,       // Return all rows
                null,
                MediaStore.Images.Media.DATE_ADDED + " DESC ");

        // Get the column index of the Thumbnails Image ID
        columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        myGridView.setAdapter(new ImageAdapter(myView.getContext(),cursor));


        /*myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Get the data location of the image
                String[] projection = {MediaStore.Images.Media.DATA};
                cursor = getActivity().managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        projection, // Which columns to return
                        null,       // Return all rows
                        null,
                        null);
                columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToPosition(position);
                // Get image filename
                String imagePath = cursor.getString(columnIndex);
                // Use this path to do further processing, i.e. full screen display
            }
        });*/

        return myView;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public int getColumnIndex() {
        return columnIndex;
    }
}
