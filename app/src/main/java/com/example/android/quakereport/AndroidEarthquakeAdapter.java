package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * {@link AndroidEarthquakeAdapter} is an{@link ArrayAdapter} that can provide a layout for each list
 * based on a data source, which is a list of {@link EarthquakeEntity} objects.
 */

public class AndroidEarthquakeAdapter extends ArrayAdapter<EarthquakeEntity> {
    Context context;
    int layoutResourceId;
    List<EarthquakeEntity> earthquake_data = null;

    public AndroidEarthquakeAdapter(Activity context, int layoutResourceId, List<EarthquakeEntity> earthquake_data) {
        super(context, layoutResourceId, earthquake_data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.earthquake_data = earthquake_data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        EarthquakeEntityHolder holder = null;
        if (listItemView == null) {
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            listItemView = layoutInflater.inflate(layoutResourceId, parent, false);

            holder = new EarthquakeEntityHolder();
            holder.date = (TextView) listItemView.findViewById(R.id.date_textView);
            holder.location = (TextView) listItemView.findViewById(R.id.location_textView);
            holder.magnitude = (TextView) listItemView.findViewById(R.id.magnitude_textView);

            listItemView.setTag(holder);


        } else {
            holder = (EarthquakeEntityHolder) listItemView.getTag();


        }
        EarthquakeEntity earthquakeEntity = earthquake_data.get(position);
        holder.location.setText(earthquakeEntity.getEarthquake_location());
        holder.magnitude.setText(String.valueOf(earthquakeEntity.getMagnitude()));
        holder.date.setText(earthquakeEntity.getDate());

        return listItemView;
    }

    static class EarthquakeEntityHolder {
        TextView date;
        TextView location;
        TextView magnitude;

    }
}
