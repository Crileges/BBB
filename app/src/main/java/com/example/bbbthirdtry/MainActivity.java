package com.example.bbbthirdtry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.bbbthirdtry.MainFragments.MapsFragment;
import com.example.bbbthirdtry.MainFragments.ProfileFragment;
import com.example.bbbthirdtry.MainFragments.Quest.Points;
import com.example.bbbthirdtry.MainFragments.Quest.Quest;
import com.example.bbbthirdtry.MainFragments.Quest.QuestsFragment;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {


    private static Context instance;
    private static MainActivity mainActivity;
    //Fragments
    Fragment mapsFragment = new MapsFragment();
    public Fragment questsFragment = new QuestsFragment();
    Fragment profileFragment = new ProfileFragment();

    public static Context getContext() {
        return instance;
    }
    public static Activity getMainActivity(){return mainActivity;}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        mainActivity = this;
        setContentView(R.layout.activity_main);

        changeToFragment(1);

        QuestList.createList();
        deleteAll();
        createTestDb();


        User.getUser();
        setTopListeners();
        setBottomNavBarListeners();
    }

    private void setTopListeners() {
        SearchView svSearchBar = (SearchView)findViewById(R.id.svSearchBar);
        Button filterBtn = (Button)findViewById(R.id.btnFilter);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestList.filter.setFilterDone(!QuestList.filter.getFilterDone());
                QuestList.updateDisplayList();
            }
        });
        svSearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                QuestList.filter.setFilterTitle(newText);
                QuestList.updateDisplayList();
                return false;
            }
        });
    }

    private void deleteAll() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.DeletAll();
        databaseHelper.close();
    }

    public void createTestDb(){

        QuestList.addOne(new Quest(1, 200, "Checkpoint Charlie", "description", "SIGHTSEEING", false, 52.5074518478684, 13.39038871954682, 1000, 20));
        QuestList.addOne(new Quest(2, 100, "Teil der Berliner Mauer", "description", "SIGHTSEEING", false, 52.509921621944194, 13.37631118215701, 1000, 30));
        QuestList.addOne(new Quest(3, 100, "Potsdamer Platz", "description", "SIGHTSEEING", false, 52.50965666305416, 13.375950004785198, 1000, 50));
        QuestList.addOne(new Quest(4, 50, "Deutsches Spionagemuseum", "description", "MUSEUM", false, 52.50879604343245, 13.379064025181943, 500, 20));
        QuestList.addOne(new Quest(5, 100, "Weltzeituhr", "description", "SIGHTSEEING", false, 52.52118053131476, 13.413319515587947, 1000, 30));
        QuestList.addOne(new Quest(6, 100, "Siegessäule", "description", "SIGHTSEEING", false, 52.51452877000894, 13.350114958019054, 2000, 100));
        QuestList.addOne(new Quest(7, 100, "Berliner Dom", "description", "SIGHTSEEING", false, 52.519048839685155, 13.400825381035075, 2000, 50));
        //Main Quests
        QuestList.addOne(new Quest(10, 200, "Brandenburger Tor", "description", "MAIN", false, 52.516326802983464, 13.377747012923937, Integer.MAX_VALUE, 50));
    }

    private void setBottomNavBarListeners() {
        ImageButton mapsBtn = (ImageButton)findViewById(R.id.ibMap);
        ImageButton questBtn = (ImageButton)findViewById(R.id.ibFlag);
        ImageButton profileBtn = (ImageButton)findViewById(R.id.ibDiamond);

        mapsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToFragment(1);
            }
        });

        questBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToFragment(2);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToFragment(3);
            }
        });
    }

    private void changeToFragment (int fragmentNumber){
        //1 = map; 2 = quest; 3 = profile
        Fragment fragment = null;
        switch (fragmentNumber){
            case 1: fragment = mapsFragment; break;
            case 2: fragment = questsFragment; break;
            case 3: fragment = profileFragment; break;
            default: throw new Error("wrong fragmentNumber");
        }
        FragmentManager manager = getSupportFragmentManager();
        androidx.fragment.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}