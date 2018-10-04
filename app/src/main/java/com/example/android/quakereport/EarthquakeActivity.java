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

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

public class EarthquakeActivity extends ListActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        //earthquake list from JSON
        List<EarthquakeEntity> list_earthquakes = QueryUtils.extractEarthquakes();
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = getListView();
        AndroidEarthquakeAdapter androidEarthquakeAdapter = new AndroidEarthquakeAdapter(this, R.layout.list_item, list_earthquakes);
        earthquakeListView.setAdapter(androidEarthquakeAdapter);


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(QueryUtils.extractEathquakeUrl(position)));
        startActivity(intent);
    }
}
