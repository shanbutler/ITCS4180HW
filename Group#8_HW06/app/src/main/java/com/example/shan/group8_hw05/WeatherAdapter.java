package com.example.shan.group8_hw05;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shan on 10/19/2016.
 * Group 8 HW 06
 * Shannon Butler
 * Akhil Ramlakan
 */

public class WeatherAdapter extends ArrayAdapter<Weather>

    {
        List<Weather> mData;
        Context mContext;
        int mResource;

        public WeatherAdapter(Context context, int resource, List<Weather> objects) {
        super(context, resource, objects);
        // resource is a layout
        this.mContext = context;
        this.mData = objects;
        this.mResource = resource;

    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }
        Bitmap image;
        Weather weather = mData.get(position);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        TextView timeTextView = (TextView) convertView.findViewById(R.id.timeTextView);
            TextView conditionTextView = (TextView) convertView.findViewById(R.id.conditionTextView);
            TextView tempTextView = (TextView) convertView.findViewById(R.id.tempTextView);
            timeTextView.setText(weather.time);
            conditionTextView.setText(weather.clouds);
            tempTextView.setText(weather.temperature + "°F");

        new AsyncGetImage(imageView, this.mContext).execute(weather.getIconUrl());
        return convertView;

            //return super.getView(position, convertView, parent);
    }
}
