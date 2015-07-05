package com.example.arjuns.homework_03;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by arjuns on 7/5/2015.
 */
public class DisplayImage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_image_layout);
        ImageView myDisplayImageView = (ImageView)findViewById(R.id.displayImageView);
        Intent displayMediaIntent = getIntent();
        //int position = myIntent.getExtras().getInt("gridPosition");
        String path = displayMediaIntent.getStringExtra("filePath");
        BitmapFactory.Options myBitmapOptions = new BitmapFactory.Options();

        myBitmapOptions.inSampleSize = 2;
        myDisplayImageView.setImageBitmap(BitmapFactory.decodeFile(path, myBitmapOptions));
    }
}
