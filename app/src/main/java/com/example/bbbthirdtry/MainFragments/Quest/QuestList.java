package com.example.bbbthirdtry.MainFragments.Quest;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QuestList {

    public static final List<Quest> ITEMS = new ArrayList<Quest>();

    static {
        int count = 12;
        for (int i = 1; i <= count; i++) {
            addItem(Quest.createTestQuest());
            //addItem(Quest.createTestQuest2(i));
        }
    }
    private static void addItem(Quest quest) {
        ITEMS.add(quest);
    }
    public static void completeQuest(int index){
        ITEMS.get(index).completeQuest();
    }

    public static int getIndex(Quest quest){
        return ITEMS.indexOf(quest);
    }
}