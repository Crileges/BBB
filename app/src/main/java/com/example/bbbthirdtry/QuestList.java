package com.example.bbbthirdtry;

import android.util.Log;

import com.example.bbbthirdtry.DatabaseHelper;
import com.example.bbbthirdtry.MainActivity;
import com.example.bbbthirdtry.MainFragments.Quest.Quest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QuestList {

    private static List<Quest> questList = new ArrayList<>();

    public static void createList(){
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.getContext());
        questList = databaseHelper.getQuests();
        databaseHelper.close();
    }

    public static void updateList(){
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.getContext());
        questList = databaseHelper.getQuests();
        databaseHelper.close();
    }

    public static List<Quest> getQuestList(){
        updateList();
        return questList;
    }

    public static boolean addOne(Quest quest){
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.getContext());
        boolean ret = databaseHelper.addOne(quest);
        updateList();
        databaseHelper.close();
        return ret;
    }

}