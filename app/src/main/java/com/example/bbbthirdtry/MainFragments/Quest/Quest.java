package com.example.bbbthirdtry.MainFragments.Quest;

import androidx.annotation.NonNull;

import java.util.Random;

public class Quest {

    String id;
    Points points;
    String title;
    String des;
    Categories category;
    boolean done;

    public Quest(String id, Points points, String title, String des, Categories category, boolean done) {
        this.id  = id;
        this.points = points;
        this.title = title;
        this.des = des;
        this.category = category;
        this.done = done;
    }

    public static Quest createTestQuest(){
        Random random = new Random();

        String [] titles = {"Märchenbrunnen", "Kletterfelsen", "Saphirbar", "Zeiss Großplanetarium"};
        Points [] points = {Points.FIFTY, Points.ONEHUDRED, Points.TWOHUNDRED};
        Categories [] category = {Categories.SIGHTSEEING, Categories.BAR, Categories.THEATER};

        String title  = titles[random.nextInt(titles.length)];
        Points point = points[random.nextInt(points.length)];
        Categories categories = category[random.nextInt(points.length)];


        return new Quest("", point, title, "", categories, false);
    }

    public static Quest createTestQuest2(int i){

        return new Quest("", Points.ONEHUDRED, Integer.toString(i), "", Categories.SIGHTSEEING, false);
    }

    public void completeQuest(){
        done = true;
    }

    @Override
    public String toString() {
        return title + " " + done;
    }
}
