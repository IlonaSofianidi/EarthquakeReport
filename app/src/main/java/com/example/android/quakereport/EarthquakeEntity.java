package com.example.android.quakereport;

class EarthquakeEntity {
    private double magnitude;
    private String earthquake_location;
    private String date;

    public EarthquakeEntity(double magnitude, String earthquake_location, String date) {
        this.magnitude = magnitude;
        this.earthquake_location = earthquake_location;
        this.date = date;
    }

    @Override
    public String toString() {
        return "EarthquakeEntity{" +
                "magnitude=" + magnitude +
                ", earthquake_location='" + earthquake_location + '\'' +
                ", date='" + date + '\'' +
                '}';
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
