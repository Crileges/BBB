package com.example.bbbthirdtry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.bbbthirdtry.MainFragments.MapsFragment;
import com.example.bbbthirdtry.MainFragments.ProfileFragment;
import com.example.bbbthirdtry.MainFragments.Quest.QuestsFragment;

public class MainActivity extends AppCompatActivity {

    //Fragments
    Fragment mapsFragment = new MapsFragment();
    Fragment questsFragment = new QuestsFragment();
    Fragment profileFragment = new ProfileFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //DatabaseConnection.connect();
        //DatabaseConnection.writeToUserTable(1);
        setBottomNavBarListeners();
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