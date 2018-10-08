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
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class EarthquakeActivity extends ListActivity implements LoaderManager.LoaderCallbacks<List<EarthquakeEntity>> {
    public static final String LOADER_LOG_TAG = "EARTHQUAKE_LOADER";
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private static final String EXTRA_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    private ProgressBar progressBar;
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        emptyTextView = (TextView) findViewById(R.id.list_view_empty);
        progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        progressBar.setVisibility(ProgressBar.VISIBLE);
        getListView().setEmptyView(emptyTextView);

        if (!isOnline()) {
            progressBar.setVisibility(ProgressBar.INVISIBLE);
            emptyTextView.setText("Check internet connection");
            Log.d(LOG_TAG, "No internet connection");
        } else {
            getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this);
            Log.d(LOADER_LOG_TAG, " loader initialization " + EARTHQUAKE_LOADER_ID);
        }
    }

    /**
     * Check the internet connection
     *
     * @return boolean
     */
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public Loader<List<EarthquakeEntity>> onCreateLoader(int i, Bundle bundle) {
        Log.d(LOADER_LOG_TAG, " loader creating");
        return new EarthquakeLoader(this, EXTRA_URL);

    }

    @Override
    public void onLoadFinished(Loader<List<EarthquakeEntity>> loader, List<EarthquakeEntity> earthquakeEntities) {
        ListView earthquakeListView = getListView();
        AndroidEarthquakeAdapter androidEarthquakeAdapter = new AndroidEarthquakeAdapter(EarthquakeActivity.this, R.layout.list_item, earthquakeEntities);
        earthquakeListView.setAdapter(androidEarthquakeAdapter);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        Log.d(LOADER_LOG_TAG, " loader load finished" + loader.getId());


    }

    @Override
    public void onLoaderReset(Loader<List<EarthquakeEntity>> loader) {
        Log.d(LOADER_LOG_TAG, " loader onReset");
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        EarthquakeEntity earthquakeEntity = (EarthquakeEntity) l.getItemAtPosition(position);
        String url = earthquakeEntity.getUrl();
        if (url != null) {
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }
}
