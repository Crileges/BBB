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
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.bbbthirdtry.MainFragments.MapsFragment;
import com.example.bbbthirdtry.MainFragments.ProfileFragment;
import com.example.bbbthirdtry.MainFragments.Quest.Points;
import com.example.bbbthirdtry.MainFragments.Quest.Quest;
import com.example.bbbthirdtry.MainFragments.Quest.QuestsFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static Context instance;
    private static MainActivity mainActivity;
    boolean filterActive = false;
    //Fragments
    MapsFragment mapsFragment = new MapsFragment();
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

        QuestList.setUpPossibleQuests();

        QuestList.createList();
        //deleteAll();
        //createTestDb();


        User.getUser();
        setTopListeners();
        setBottomNavBarListeners();
    }

    private void setTopListeners() {
        SearchView svSearchBar = findViewById(R.id.svSearchBar);
        Button filterBtn = findViewById(R.id.btnFilter);
        ImageView filterIcon = findViewById(R.id.ivFilterBtn);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filterActive){
                    filterIcon.setBackgroundResource(R.drawable.filter);
                } else {
                    filterIcon.setBackgroundResource(R.drawable.closex);
                }
                filterActive = !filterActive;
                QuestList.filter.setFilterDone(!QuestList.filter.getFilterDone());
                QuestList.updateDisplayList();
                mapsFragment.loadMarkers();
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
                mapsFragment.loadMarkers();
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

        //Main Quests
        QuestList.addOne(new Quest(0, 200, "Brandenburger Tor", "description", "MAIN", false, 52.516326802983464, 13.377747012923937, Integer.MAX_VALUE, 30));
        QuestList.addOne(new Quest(1, 200, "Fernsehturm", "description", "MAIN", false, 52.52089331038345, 13.409494199433652, Integer.MAX_VALUE, 30));
        QuestList.addOne(new Quest(2, 200, "Kurfürstendamm", "description", "MAIN", false, 52.50460146835356, 13.33510963171975, Integer.MAX_VALUE, 200));
        QuestList.addOne(new Quest(3, 200, "Schloss Charlottenburg", "description", "MAIN", false, 52.51670034147666, 13.304078423736792, Integer.MAX_VALUE, 30));
        QuestList.addOne(new Quest(4, 200, "Schloss Schönhausen", "description", "MAIN", false, 52.57900199927118, 13.405272277984105, Integer.MAX_VALUE, 30));
        QuestList.addOne(new Quest(5, 200, "Mustafa's Gemüse Kebap", "description", "MAIN", false, 52.494349136065665, 13.388105268070634, Integer.MAX_VALUE, 30));
        QuestList.addOne(new Quest(6, 200, "Oberbaumbrücke", "description", "MAIN", false, 52.5017754462656, 13.445614006524359, Integer.MAX_VALUE, 30));
        QuestList.addOne(new Quest(7, 200, "KulturBrauerei", "description", "MAIN", false, 52.53822927444323, 13.412756212925057, Integer.MAX_VALUE, 30));
        //Main Quests

        List<Quest> possibleQuests = QuestList.getPossibleQuests();
        for (Quest quest: possibleQuests)   {
            QuestList.addOne(quest);
        }
    }

    private void setBottomNavBarListeners() {
        ImageButton mapsBtn = findViewById(R.id.ibMap);
        ImageButton questBtn = findViewById(R.id.ibFlag);
        ImageButton profileBtn = findViewById(R.id.ibDiamond);

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