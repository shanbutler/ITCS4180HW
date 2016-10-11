package com.example.shan.group8_hw05;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CityWeather extends AppCompatActivity implements AsyncGetJSON.IData {

    ProgressDialog dialog;
    String city, state, strUrl;
    ArrayList<Weather> weathers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);

        Bundle extras = getIntent().getExtras();

        if(getIntent().getExtras() != null) {
            strUrl = getIntent().getExtras().getString("url");
            state = getIntent().getExtras().getString("state");
            city = getIntent().getExtras().getString("city");
        }

        // set up loading message
        dialog = new ProgressDialog(CityWeather.this);
        dialog.setMessage("Loading Hourly Data");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show(); // show right away

        new AsyncGetJSON(CityWeather.this).execute(strUrl);



    }

    public void setupData(ArrayList<Weather> result)
    {

        dialog.dismiss();
        weathers = result;

        /*
        ListView listview = (ListView) findViewById(R.id.listView);

        PodcastAdapter adapter = new PodcastAdapter(this, R.layout.row_item_layout, podcasts);
        listview.setAdapter(adapter);

        adapter.setNotifyOnChange((true)); // automatically updates on changes
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                // click on it, get its position in array
                                                Log.d("demo", "Position: " + position + ", Value: " + podcasts.get(position));

                                                Intent intent = new Intent(MainActivity.this, ItemDetails.class);
                                                intent.putExtra(PODCASTS_KEY, podcasts.get(position));
                                                startActivity(intent);
                                            }


                                        }
        );
*/

    }
}
