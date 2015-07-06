package com.example.arjuns.homework_03;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
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
        String[] projection = {MediaStore.Images.Media.DATA,
                MediaStore.Images.Media._ID,
                MediaStore.Files.FileColumns.DATE_MODIFIED,
                MediaStore.Files.FileColumns.MEDIA_TYPE,
                MediaStore.Files.FileColumns.MIME_TYPE,
                MediaStore.Files.FileColumns.TITLE};
        String selectionQuery = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;
        final String sortDescending = MediaStore.Files.FileColumns.DATE_ADDED + " DESC";

        // Create the cursor pointing to the SDCard
        cursor = getActivity().managedQuery(MediaStore.Files.getContentUri("external"),
                projection, // Which columns to return
                selectionQuery,       // Return all rows
                null,
                sortDescending);

        // Get the column index of the Thumbnails Image ID
        //columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        int numberOfFiles =cursor.getCount();
        String[] filePaths = new String[numberOfFiles];
        String[] fileNames = new String[numberOfFiles];
        final int[] fileNameExtensions = new int[numberOfFiles];
        for (int i = 0; i < numberOfFiles; i++) {
            cursor.moveToPosition(i);

            int columnIndex = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
            int fileNamesIndex = cursor.getColumnIndex(MediaStore.Files.FileColumns.TITLE);
            int extensionsIndex = cursor.getColumnIndex(MediaStore.Files.FileColumns.MEDIA_TYPE);

            // Get the path of the image file
            filePaths[i] = cursor.getString(columnIndex);
            // Get the name image file
            fileNames[i] = cursor.getString(fileNamesIndex);
            // Get the name image extension
            fileNameExtensions[i] = cursor.getInt(extensionsIndex);
        }

        myGridView.setAdapter(new ImageAdapter(myView.getContext(), cursor, fileNameExtensions));

        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Get the data location of the image
                columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);
                cursor.moveToPosition(position);
                // Get image filename
                String mediaPath = cursor.getString(columnIndex);
                // Use this path to do further processing, i.e. full screen display
                if(fileNameExtensions[position] == 3) {
                    Intent displayVideoIntent = new Intent(getActivity().getApplicationContext(), DisplayVideo.class);
                    displayVideoIntent.putExtra("filePath", mediaPath);
                    startActivity(displayVideoIntent);
                }
                else {
                    Intent displayMediaIntent = new Intent(getActivity().getApplicationContext(), DisplayImage.class);
                    displayMediaIntent.putExtra("filePath", mediaPath);
                    startActivity(displayMediaIntent);
                }
            }
        });

        return myView;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public int getColumnIndex() {
        return columnIndex;
    }
}
