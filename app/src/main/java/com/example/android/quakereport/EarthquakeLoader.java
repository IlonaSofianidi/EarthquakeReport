package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<EarthquakeEntity>> {
    private String url;


    public EarthquakeLoader(Context context, String url) {
        super(context);
        this.url = url;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.d(EarthquakeActivity.LOADER_LOG_TAG, " start loading");
    }

    @Override
    public List<EarthquakeEntity> loadInBackground() {
        if (url.isEmpty()) {
            Log.d(EarthquakeActivity.LOADER_LOG_TAG, " load inBackground empty url");
            return null;
        }
        List<EarthquakeEntity> list = QueryUtils.fetchEarthquakeData(url);
        Log.d(EarthquakeActivity.LOADER_LOG_TAG, "loadInBackground");
        return list;
    }
}
