package com.example.android.quakereport;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /**
     * Sample JSON response for a USGS query
     */


    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Fetch {@link} data by url
     *
     * @param request_url
     * @return List<EarthquakeEntity>
     */
    public static List<EarthquakeEntity> fetchEarthquakeData(String request_url) {

        //Create url object
        URL url = createUrl(request_url);
        String jsonResponse = null;

        // Perform HTTP request to the URL and receive a JSON response back
        try {
            jsonResponse = makeHttpRequest(url);

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("IOException", "Error closing input stream " + e);
        }
        List<EarthquakeEntity> earthquakeEntityList = extractEarthquakes(jsonResponse);
        Log.d("Utils", "earthquake data fetching");


        return earthquakeEntityList;

    }

    /**
     * Make URL object from string
     *
     * @param request_url
     * @return URL
     */
    private static URL createUrl(String request_url) {
        URL url = null;
        try {
            url = new URL(request_url);
        } catch (MalformedURLException ex) {
            Log.e("URL", "Error creating url");
        }
        return url;
    }

    /**
     * Make http request by {@link URL}
     *
     * @param url
     * @return String
     * @throws IOException
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("RESPONSE", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("RESPONSE", "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;


    }

    /**
     * Read inputstram and convert it to string
     *
     * @param inputStream
     * @return String
     * @throws IOException
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        while (line != null) {
            stringBuilder.append(line);
            line = reader.readLine();
        }
        return stringBuilder.toString();

    }

    /**
     * Return a list of {@link EarthquakeEntity} objects that has been built up from
     * parsing a JSON response.
     */
    private static ArrayList<EarthquakeEntity> extractEarthquakes(String jsonResponse) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<EarthquakeEntity> earthquakes = new ArrayList<>();


        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray features = jsonObject.getJSONArray("features");

            for (int i = 0; i < features.length(); i++) {
                JSONObject earthquake_object = features.getJSONObject(i);
                JSONObject properties = earthquake_object.getJSONObject("properties");

                String url = properties.getString("url");
                double magnitude = properties.getDouble("mag");
                String location = properties.getString("place");
                long date_earthquake = properties.getLong("time");
                EarthquakeEntity earthquakeEntity = new EarthquakeEntity(magnitude, location, date_earthquake,url);
                earthquakes.add(earthquakeEntity);
            }


            // build up a list of Earthquake objects with the corresponding data.

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }



}
