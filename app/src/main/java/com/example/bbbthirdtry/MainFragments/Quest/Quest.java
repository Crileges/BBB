package com.example.bbbthirdtry.MainFragments.Quest;

import androidx.annotation.NonNull;

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
    int radius;




    public Quest(int id, Points points, String title, String des, Categories category,boolean done, double lat, double lon, int radius) {
        this.id  = id;
        this.points = points;
        this.title = title;
        this.des = des;
        this.category = category;
        this.done = done;
        this.lat = lat;
        this.lon = lon;
        this.radius = radius;
    }

    public static Quest createTestQuest(){
        Random random = new Random();

        String [] titles = {"Märchenbrunnen", "Kletterfelsen", "Saphirbar", "Zeiss Großplanetarium"};
        Points [] points = {Points.FIFTY, Points.ONEHUDRED, Points.TWOHUNDRED};
        Categories [] category = {Categories.SIGHTSEEING, Categories.BAR, Categories.THEATER};

        String title  = titles[random.nextInt(titles.length)];
        Points point = points[random.nextInt(points.length)];
        Categories categories = category[random.nextInt(points.length)];


        return new Quest(0, point, title, "", categories,false,  0, 0, 10);
    }

    public static Quest createTestQuest2(int i){

        return new Quest(0, Points.ONEHUDRED, Integer.toString(i), "", Categories.SIGHTSEEING, false, 0, 0, 10);
    }

    public void completeQuest(){
        done = true;
    }

    @Override
    public String toString() {
        return title + " " + done;
    }
    public int getId() {
        return id;
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

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
