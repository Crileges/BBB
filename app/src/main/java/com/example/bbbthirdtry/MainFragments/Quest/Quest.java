package com.example.bbbthirdtry.MainFragments.Quest;

import java.util.Random;

public class Quest {

    int id;
    Points points;
    String title;
    String des;
    Categories category;
    boolean done;
    double lat;
    double lon;
    int popupRadius;
    int claimRadius;




    public Quest(int id, Points points, String title, String des, Categories category,boolean done, double lat, double lon, int popupRadius, int claimRadius) {
        this.id  = id;
        this.points = points;
        this.title = title;
        this.des = des;
        this.category = category;
        this.done = done;
        this.lat = lat;
        this.lon = lon;
        this.popupRadius = popupRadius;
        this.claimRadius = claimRadius;
    }

    public Quest(int id, int points, String title, String des, String category,boolean done, double lat, double lon, int popupRadius, int claimRadius) {
        this.id  = id;
        this.points = Points.convertToPoints(points);
        this.title = title;
        this.des = des;
        this.category = Categories.convertToCategory(category);
        this.done = done;
        this.lat = lat;
        this.lon = lon;
        this.popupRadius = popupRadius;
        this.claimRadius = claimRadius;
    }

    @Override
    public String toString() {
        return title + " " + done;
    }
    public int getId() {
        return id;
    }

    public void setPopupRadius(int popupRadius) {
        this.popupRadius = popupRadius;
    }

    public int getClaimRadius() {
        return claimRadius;
    }

    public void setClaimRadius(int claimRadius) {
        this.claimRadius = claimRadius;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getPopupRadius() {
        return popupRadius;
    }

    public void getPopupRadius(int radius) {
        this.popupRadius = radius;
    }
}
