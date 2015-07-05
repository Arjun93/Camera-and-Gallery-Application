package com.example.arjuns.homework_03;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by arjuns on 7/2/2015.
 */
public class ImageAdapter extends BaseAdapter{
    private Context mContext;
    private Cursor myImageCursor;
    Tab1Fragment myFetcher;
    private static LayoutInflater myInflater;
    ImageView myGridImageView;

    // Constructor
    public ImageAdapter(Context c, Cursor cursor){
        mContext = c;
        myImageCursor = cursor;
        myInflater =  (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myImageCursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        BitmapFactory.Options myBitmapOptions = new BitmapFactory.Options();
        myBitmapOptions.inSampleSize = 8;
        if (view == null) {
            view = myInflater.inflate(R.layout.grid_view_element, null);
        }
            myGridImageView = (ImageView)view.findViewById(R.id.myGridImageView);
            // Move cursor to current position
            myImageCursor.moveToPosition(position);
            // Get the current value for the requested column
            int myColumnIndex = myImageCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String imagePath = myImageCursor.getString(myColumnIndex);
            File myFile = new File(imagePath);
            Log.i("IMAGEPATH",""+imagePath);
            if(myFile.exists())
            {
               //myGridImageView.setImageURI(Uri.parse(imagePath));
               myGridImageView.setImageBitmap(BitmapFactory.decodeFile(imagePath, myBitmapOptions));
            }

        return view;
    }

}
