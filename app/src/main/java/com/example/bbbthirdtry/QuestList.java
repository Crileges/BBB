package com.example.bbbthirdtry;

import android.util.Log;

import com.example.bbbthirdtry.MainFragments.Quest.Points;
import com.example.bbbthirdtry.MainFragments.Quest.Quest;
import com.example.bbbthirdtry.MainFragments.Quest.QuestRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class QuestList {

    private static List<Quest> questList = new ArrayList<>();
    private static List<Quest> displayList = new ArrayList<>();

    private static final List<Quest> possibleQuests = new ArrayList<>();

    public static Filter filter;

    public static Quest currentQuest = null;

    public static List<Quest> getPossibleQuests(){
        return possibleQuests;
    }
    public static List<Quest> getQuestList(){return questList;}

    public static void setUpPossibleQuests(){
        possibleQuests.add((new Quest(11, 200, "Checkpoint Charlie", "description", "SIGHTSEEING", false, 52.5074518478684, 13.39038871954682, 1000, 20)));
        possibleQuests.add((new Quest(12, 100, "Teil der Berliner Mauer", "description", "SIGHTSEEING", false, 52.509921621944194, 13.37631118215701, 1000, 30)));
        possibleQuests.add((new Quest(13, 100, "Potsdamer Platz", "description", "SIGHTSEEING", false, 52.50965666305416, 13.375950004785198, 1000, 50)));
        possibleQuests.add((new Quest(14, 50, "Deutsches Spionagemuseum", "description", "MUSEUM", false, 52.50879604343245, 13.379064025181943, 500, 20)));
        possibleQuests.add((new Quest(15, 100, "Weltzeituhr", "description", "SIGHTSEEING", false, 52.52118053131476, 13.413319515587947, 1000, 30)));
        possibleQuests.add((new Quest(16, 100, "Siegessäule", "description", "SIGHTSEEING", false, 52.51452877000894, 13.350114958019054, 2000, 100)));
        possibleQuests.add((new Quest(17, 100, "Berliner Dom", "description", "SIGHTSEEING", false, 52.519048839685155, 13.400825381035075, 2000, 50)));
        possibleQuests.add((new Quest(18, 50, "Max und Moritz", "description", "FOOD", false, 52.53386455355585, 13.436680147659208, 500, 20)));
        possibleQuests.add((new Quest(19, 50, "Markthalle Neun", "description", "FOOD", false, 52.50207562003517, 13.431924468601116, 500, 35)));
        possibleQuests.add((new Quest(20, 50, "Kletterfelsen", "description", "SIGHTSEEING", false, 52.528187938698494, 13.441261222759106, 500, 50)));
        possibleQuests.add((new Quest(21, 50, "BurgerAMT", "description", "FOOD", false, 52.51024275236238, 13.459614990134558, 500, 20)));
        possibleQuests.add((new Quest(22, 50, "Rüyam Gemüse Kebab", "description", "FOOD", false, 52.48467988853361, 13.35391909693803, 500, 40)));
        possibleQuests.add((new Quest(23, 50, "Konopke’s Imbiss", "description", "FOOD", false, 52.54043269944165, 13.41217908727278, 500, 500)));
        possibleQuests.add((new Quest(24, 50, "ZOLA", "description", "FOOD", false, 52.49609656595152, 13.422335884596867, 500, 30)));
        possibleQuests.add((new Quest(25, 50, "Curry 36", "description", "FOOD", false, 52.49347321664647, 13.387699121005994, 500, 20)));
        possibleQuests.add((new Quest(26, 50, "Ratibortheater", "description", "THEATER", false, 52.49739217205829, 13.443421079644898, 500, 50)));
    }

    public static void createList(){
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.getContext());
        questList = databaseHelper.getQuests();
        filter = new Filter();
        displayList = questList;
        QuestRecyclerViewAdapter.setList(displayList);
        updateDisplayList();
        databaseHelper.close();
    }

    public static boolean checkIfQuestsExists(Quest testQuest){
        String title = testQuest.getTitle();
        for (Quest quest: questList) {
            if(quest.getTitle() == title){
                return true;
            }
        }
        return false;
    }

    public static void updateList(){
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.getContext());
        questList = databaseHelper.getQuests();
        databaseHelper.close();
    }

    public static void updateDisplayList(){
        displayList.clear();
        for (Quest quest: questList) {

            if(!filter.getFilterTitle().equals("")){
                if(!quest.getTitle().toLowerCase().contains(filter.filterTitle.toLowerCase())){
                    continue;
                }
            }
            if(filter.filterDone){
                if(quest.isDone()){
                    continue;
                }
            }
            displayList.add(quest);
        }
        sortList();
        QuestRecyclerViewAdapter.setList(displayList);
    }

    private static void sortList() {
        Collections.sort(displayList, new Comparator<Quest>() {
            @Override
            public int compare(Quest o1, Quest o2) {
                return -Boolean.compare(o1.isDone(), o2.isDone());
            }
        });
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
            if(quest.getTitle().equals(title)){
                return quest;
            }
        }
        return null;
    }
}