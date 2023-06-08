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

public class MainActivity extends AppCompatActivity {


    private static Context instance;
    private static MainActivity mainActivity;
    boolean filterActive = false;
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
        QuestList.addOne(new Quest(8, 50, "Max und Moritz", "description", "FOOD", false, 52.53386455355585, 13.436680147659208, 500, 20));
        QuestList.addOne(new Quest(9, 50, "Markthalle Neun", "description", "FOOD", false, 52.50207562003517, 13.431924468601116, 500, 35));
        //Main Quests
        QuestList.addOne(new Quest(10, 200, "Brandenburger Tor", "description", "MAIN", false, 52.516326802983464, 13.377747012923937, Integer.MAX_VALUE, 30));
        QuestList.addOne(new Quest(11, 200, "Fernsehturm", "description", "MAIN", false, 52.52089331038345, 13.409494199433652, Integer.MAX_VALUE, 30));
        QuestList.addOne(new Quest(12, 200, "Kurfürstendamm", "description", "MAIN", false, 52.50460146835356, 13.33510963171975, Integer.MAX_VALUE, 200));
        QuestList.addOne(new Quest(13, 200, "Schloss Charlottenburg", "description", "MAIN", false, 52.51670034147666, 13.304078423736792, Integer.MAX_VALUE, 30));
        QuestList.addOne(new Quest(13, 200, "Schloss Schönhausen", "description", "MAIN", false, 52.57900199927118, 13.405272277984105, Integer.MAX_VALUE, 30));
        //Main Quests
        QuestList.addOne(new Quest(21, 50, "BurgerAMT", "description", "FOOD", false, 52.51024275236238, 13.459614990134558, 500, 20));
        QuestList.addOne(new Quest(22, 50, "Rüyam Gemüse Kebab", "description", "FOOD", false, 52.48467988853361, 13.35391909693803, 500, 40));
        QuestList.addOne(new Quest(23, 50, "Konopke’s Imbiss", "description", "FOOD", false, 52.54043269944165, 13.41217908727278, 500, 30));
        QuestList.addOne(new Quest(24, 50, "ZOLA", "description", "FOOD", false, 52.49609656595152, 13.422335884596867, 500, 30));
        QuestList.addOne(new Quest(25, 50, "Curry 36", "description", "FOOD", false, 52.49347321664647, 13.387699121005994, 500, 20));
        QuestList.addOne(new Quest(26, 50, "Ratibortheater", "description", "THEATER", false, 52.49739217205829, 13.443421079644898, 500, 50));
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