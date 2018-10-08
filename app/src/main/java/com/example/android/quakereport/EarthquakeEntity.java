package com.example.android.quakereport;

class EarthquakeEntity {
    private double magnitude;
    private String earthquake_location;
    private long date;


    public EarthquakeEntity(double magnitude, String earthquake_location, long date) {
        this.magnitude = magnitude;
        this.earthquake_location = earthquake_location;
        this.date = date;
    }

    private String url;

    @Override
    public String toString() {
        return "EarthquakeEntity{" +
                "magnitude=" + magnitude +
                ", earthquake_location='" + earthquake_location + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public EarthquakeEntity(double magnitude, String earthquake_location, long date, String url) {
        this.magnitude = magnitude;
        this.earthquake_location = earthquake_location;
        this.date = date;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getEarthquake_location() {
        return earthquake_location;
    }

    public void setEarthquake_location(String earthquake_location) {
        this.earthquake_location = earthquake_location;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
