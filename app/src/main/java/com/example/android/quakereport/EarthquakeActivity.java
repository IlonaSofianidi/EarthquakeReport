/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends Activity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
//C///create a fake list of earthquakes.
        List<EarthquakeEntity> list_of_earthquakes = new ArrayList<>();
        list_of_earthquakes.add(new EarthquakeEntity(7.6, "San Francisco", "25.09.2018"));
        list_of_earthquakes.add(new EarthquakeEntity(5.4, "London", "12.05.2018"));
        list_of_earthquakes.add(new EarthquakeEntity(3.3, "Tokyo", "01.10.2018"));
        list_of_earthquakes.add(new EarthquakeEntity(7.8, "Mexico City", "13.02.2018"));
        list_of_earthquakes.add(new EarthquakeEntity(9.9, "Moscow", "31.05.2018"));
        list_of_earthquakes.add(new EarthquakeEntity(7.9, "Paris", "05.05.2018"));


        // Create a fake list of earthquake locations.
        ArrayList<String> earthquakes = new ArrayList<>();
        earthquakes.add("San Francisco");
        earthquakes.add("London");
        earthquakes.add("Tokyo");
        earthquakes.add("Mexico City");
        earthquakes.add("Moscow");
        earthquakes.add("Rio de Janeiro");
        earthquakes.add("Paris");


        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        AndroidEarthquakeAdapter androidEarthquakeAdapter = new AndroidEarthquakeAdapter(this, R.layout.list_item, list_of_earthquakes);
        earthquakeListView.setAdapter(androidEarthquakeAdapter);


        // Create a new {@link ArrayAdapter} of earthquakes
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        //       this, R.layout.list_item,earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        // earthquakeListView.setAdapter(adapter);
    }
}
