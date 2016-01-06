package com.example.equipe1.webservice;

/**
 * Created by Cricri on 06/01/2016.
 */
public class Tremblement {
    private String date_time;
    private String longitude;
    private String latitude;
    private String depth;
    private String location;

    @Override
    public String toString() {
        return "Tremblement{" +
                "date_time='" + date_time + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", depth='" + depth + '\'' +
                ", location='" + location + '\'' +
                ", magnitude='" + magnitude + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String magnitude;
    private String title;


}
