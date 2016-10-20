package com.example.shan.group8_hw05;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Shan on 10/19/2016.
 * Group 8 HW 06
 * Shannon Butler
 * Akhil Ramlakan
 */

public class AsyncGetImage extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;
    Context context;
    private ProgressDialog dialog;

    public AsyncGetImage(ImageView image, Context context) {
        this.imageView = image;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        ///dialog = ProgressDialog.show(context, "", "Loading image", true, false);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String url = params[0];
        Bitmap image = null;

        try {
            InputStream in = new java.net.URL(url).openStream();
            image = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("error", e.getMessage());
            e.printStackTrace();
        }

        return image;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    // allows access to mainactivity variables and methods
    static public interface IData
    {
        public void setupData(ArrayList<Weather> result);
    }
}
