package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * {@link AndroidEarthquakeAdapter} is an{@link ArrayAdapter} that can provide a layout for each list
 * based on a data source, which is a list of {@link EarthquakeEntity} objects.
 */

public class AndroidEarthquakeAdapter extends ArrayAdapter<EarthquakeEntity> {
    Context context;
    int layoutResourceId;
    List<EarthquakeEntity> earthquake_data = null;

    private static final String LOCATION_SEPARATOR = " of ";

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
            holder.time = (TextView) listItemView.findViewById(R.id.time_textView);
            holder.location_km = (TextView) listItemView.findViewById(R.id.location_km_textView);

            listItemView.setTag(holder);


        } else {
            holder = (EarthquakeEntityHolder) listItemView.getTag();


        }
        EarthquakeEntity earthquakeEntity = earthquake_data.get(position);
        Drawable magnitudeCircle = holder.magnitude.getBackground();


        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquakeEntity.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.mutate().setColorFilter(magnitudeColor, PorterDuff.Mode.MULTIPLY);


        holder.magnitude.setText(formatDecimel(earthquakeEntity.getMagnitude()));
        String[] locationParts = splitLocation(earthquakeEntity.getEarthquake_location());
        holder.location_km.setText(locationParts[0] + LOCATION_SEPARATOR);
        holder.location.setText(locationParts[1]);

        holder.date.setText(formatDate(earthquakeEntity.getDate()));
        holder.time.setText(formatTime(earthquakeEntity.getDate()));

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColor = 0;
        int magnitudeFloor = (int) Math.floor(magnitude);
        Log.e("Colllo", String.valueOf(magnitudeFloor));
        switch (magnitudeFloor) {
            case 0:
                magnitudeColor = R.color.magnitude1;
                break;
            case 1:
                magnitudeColor = R.color.magnitude2;
                break;
            case 3:
                magnitudeColor = R.color.magnitude3;
                break;
            case 4:
                magnitudeColor = R.color.magnitude4;
                break;
            case 5:
                magnitudeColor = R.color.magnitude5;
                break;
            case 6:
                magnitudeColor = R.color.magnitude6;
                break;
            case 7:
                magnitudeColor = R.color.magnitude7;
                break;
            case 8:
                magnitudeColor = R.color.magnitude8;
                break;
            case 9:
                magnitudeColor = R.color.magnitude9;
                break;
            case 10:
                magnitudeColor = R.color.magnitude10plus;
                break;
            default:
                magnitudeColor = R.color.colorPrimaryDark;

        }
        return ContextCompat.getColor(getContext(), magnitudeColor);

    }

    private String formatDecimel(double decimel) {
        DecimalFormat formatter = new DecimalFormat("0.0");

        return formatter.format(decimel);
    }

    private String[] splitLocation(String location) {
        String[] parts;
        if (location.contains(LOCATION_SEPARATOR)) {
            parts = location.split(LOCATION_SEPARATOR, 2);

        } else {
            parts = new String[]{"near of", location};
        }
        return parts;
    }

    private String formatTime(long date) {
        Date date_format = new Date(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
        String time_string = simpleDateFormat.format(date_format);
        return time_string;
    }

    private String formatDate(long date) {
        Date date_format = new Date(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("LLL dd, yyyy");
        String date_string = simpleDateFormat.format(date_format);
        return date_string;
    }

    static class EarthquakeEntityHolder {
        TextView time;
        TextView date;
        TextView location;
        TextView magnitude;
        TextView location_km;

    }
}
