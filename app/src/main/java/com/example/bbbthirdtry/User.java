package com.example.bbbthirdtry;

import static android.content.Context.LOCATION_SERVICE;
import static androidx.core.location.LocationManagerCompat.requestLocationUpdates;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.bbbthirdtry.MainFragments.MapsFragment;
import com.example.bbbthirdtry.MainFragments.Quest.Quest;
import com.example.bbbthirdtry.MainFragments.Quest.QuestsFragment;

import java.sql.Connection;

public class User implements LocationListener {
    public int points = 0;
    public Location currentLocation;
    public String name;
    public static User user;
    public static boolean created = false;

    public User(String name) {

        created = true;
        user = this;
        this.name = name;
        LocationListener listener = this;
        LocationManager locationManager = (LocationManager) MainActivity.getMainActivity().getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(MainActivity.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.getMainActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            MapsFragment mapsFragment = MainActivity.getMapsFragment();
            mapsFragment.enableMyLocation();
        }

        //GPS on Emulator
        Log.d("testLocation", "test1");
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
        //Network only on Real Device
        Log.d("testLocation", "test2");
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
    }

    public static User getUser(){
        if(!created){
            return new User("TestUser1");
        } else {
            return user;
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        User.getUser().currentLocation = location;
        if(QuestList.getPossibleQuests().size()>1){
            for (Quest quest: QuestList.getPossibleQuests()) {
                Location targetLocation = new Location("");
                targetLocation.setLatitude(quest.getLat());
                targetLocation.setLongitude(quest.getLon());
                float distanceInMeters =  targetLocation.distanceTo(location);
                if(distanceInMeters <= quest.getPopupRadius()){
                    QuestList.addOne(quest);
                    int index = QuestList.getPossibleQuests().indexOf(quest);
                    Log.d("testLocation", Integer.toString(index));
                    QuestList.getPossibleQuests().remove(index);
                    return;
                }
            }
        }
    }
}
