package com.example.shan.hw4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by akhil on 9/25/2016.
 */
public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;

    public DownloadImage(ImageView image) {
        this.imageView = image;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String url = params[0];
        Bitmap triviaImage = null;

        try {
            InputStream in = new java.net.URL(url).openStream();
            triviaImage = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("error", e.getMessage());
            e.printStackTrace();
        }

        return triviaImage;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
