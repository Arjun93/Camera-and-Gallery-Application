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

        //Bitmap resultantBitmap = decodeSampledBitmapFromResource(getResources(), Uri.parse(path), 100, 100);

        myDisplayImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        myDisplayImageView.setImageURI(Uri.parse(path));
        //myDisplayImageView.setImageBitmap(resultantBitmap);
        myDisplayImageView.setPadding(8, 8, 8, 8);
        myDisplayImageView.setAdjustViewBounds(true);

    }

    /*public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int requiredWidth, int requiredHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize   
        options.inSampleSize = calculateInSampleSize(options, requiredWidth, requiredHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int requiredWidth, int requiredHeight) {
            // Raw height and width of image
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if (height > requiredHeight || width > requiredWidth) {

                final int halfHeight = height / 2;
                final int halfWidth = width / 2;

                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                while ((halfHeight / inSampleSize) > requiredHeight
                        && (halfWidth / inSampleSize) > requiredWidth) {
                    inSampleSize *= 2;
                }
            }

            return inSampleSize;
    }*/

}
