package com.example.bbbthirdtry.MainFragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bbbthirdtry.MainActivity;
import com.example.bbbthirdtry.MainFragments.Quest.Quest;
import com.example.bbbthirdtry.PermissionUtils;
import com.example.bbbthirdtry.QuestList;
import com.example.bbbthirdtry.R;
import com.example.bbbthirdtry.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    static GoogleMap googleMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @SuppressLint("MissingPermission")
        @Override
        public void onMapReady(GoogleMap googleMap) {
            MapsFragment.googleMap = googleMap;
            enableMyLocation();
            loadMarkers();
            LatLng myLocation = new LatLng(52.51729131360452, 13.406878968403063);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 13));
        }
    };

    public void loadMarkers(){
        if(googleMap != null){
            googleMap.clear();
        }

        for (Quest quest: QuestList.getDisplayList()) {
            LatLng markerPos = new LatLng(quest.getLat(), quest.getLon());
            int height = 120;
            int width = 96;
            int bitmapId = 0;
            switch (quest.getCategory()){
                case SIGHTSEEING:bitmapId = R.drawable.camaramarker; break;
                case MAIN: bitmapId = R.drawable.mainmarker; break;
                case MUSEUM: bitmapId = R.drawable.museummarker; break;
                case THEATER: bitmapId = R.drawable.theatermarker; break;
                case FOOD: bitmapId = R.drawable.foodmarker; break;
            }
            Bitmap b = BitmapFactory.decodeResource(getResources(), bitmapId);
            Bitmap markerIcon = Bitmap.createScaledBitmap(b, width, height, false);
            if(googleMap != null){
                MarkerOptions marker1 = new MarkerOptions().position(markerPos).title(quest.getTitle()).icon(BitmapDescriptorFactory.fromBitmap(markerIcon));
                googleMap.addMarker(marker1);
            }
        }
        if(googleMap != null){
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {
                    changeCurrentElement(marker);
                    changeTopNavBar();
                    return false;
                }
            });
        }

    }

    private static void changeCurrentElement(Marker marker) {
        QuestList.currentQuest = QuestList.findQuestByTitle(marker.getTitle());
    }

    private static void changeTopNavBar() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapsFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
            loadMarkers();
        }
    }

    @SuppressLint("MissingPermission")
    public void enableMyLocation() {
        // [START maps_check_location_permission]
        // 1. Check if permissions are granted, if so, enable the my location layer
        if (ContextCompat.checkSelfPermission(MainActivity.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MainActivity.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
            return;
        }
        PermissionUtils.requestLocationPermissions(MainActivity.getMainActivity(), LOCATION_PERMISSION_REQUEST_CODE, true);
    }


}