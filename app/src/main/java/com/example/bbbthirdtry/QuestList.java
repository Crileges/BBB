package com.example.bbbthirdtry;

import android.util.Log;

import com.example.bbbthirdtry.DatabaseHelper;
import com.example.bbbthirdtry.MainActivity;
import com.example.bbbthirdtry.MainFragments.Quest.Points;
import com.example.bbbthirdtry.MainFragments.Quest.Quest;
import com.example.bbbthirdtry.MainFragments.Quest.QuestRecyclerViewAdapter;
import com.example.bbbthirdtry.MainFragments.Quest.QuestsFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QuestList {

    private static List<Quest> questList = new ArrayList<>();
    private static List<Quest> displayList = new ArrayList<>();

    public static Filter filter;

    public static Quest currentQuest = null;



    public static void createList(){
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.getContext());
        questList = databaseHelper.getQuests();
        filter = new Filter();
        displayList = questList;
        QuestRecyclerViewAdapter.list = displayList;
        databaseHelper.close();
    }

    public static void updateList(){
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.getContext());
        questList = databaseHelper.getQuests();
        databaseHelper.close();
    }

    public static void updateDisplayList(){
        displayList.clear();
        for (Quest quest: questList) {

            if(filter.getFilterTitle() != ""){
                if(!quest.getTitle().toLowerCase().contains(filter.filterTitle.toLowerCase())){
                    continue;
                }
            }
            if(filter.filterDone != false){
                if(quest.isDone()){
                    continue;
                }
            }
            displayList.add(quest);
        }
        QuestRecyclerViewAdapter.setList(displayList);


    }

    public static Quest getQuestById(int id){
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.getContext());
        Quest quest = databaseHelper.getOneQuest(id);
        databaseHelper.close();
        return quest;
    }

    public static List<Quest> getDisplayList(){
        updateList();
        updateDisplayList();
        return displayList;
    }

    public static void completeQuest(Quest quest){
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.getContext());
        databaseHelper.completeQuest(quest);
        User.getUser().points += Points.getIntFromValue(quest.getPoints());
        updateList();
        databaseHelper.close();
    }

    public static boolean addOne(Quest quest){
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.getContext());
        boolean ret = databaseHelper.addOne(quest);
        updateList();
        updateDisplayList();
        databaseHelper.close();
        return ret;
    }

    public static Quest findQuestByTitle(String title){
        for (Quest quest: QuestList.getDisplayList()) {
            if(quest.getTitle() == title){
                return quest;
            }
        }
        return null;
    }
}