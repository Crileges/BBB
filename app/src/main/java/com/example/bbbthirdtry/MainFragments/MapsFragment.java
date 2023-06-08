package com.example.bbbthirdtry.MainFragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bbbthirdtry.MainFragments.Quest.Quest;
import com.example.bbbthirdtry.QuestList;
import com.example.bbbthirdtry.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            loadMarkers(googleMap);

            LatLng berlin = new LatLng(52.520008, 13.404954);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(berlin, 12));

        }
    };

    public void loadMarkers(GoogleMap googleMap){
        for (Quest quest: QuestList.getDisplayList()) {
            LatLng markerPos = new LatLng(quest.getLat(), quest.getLon());
            int height = 150;
            int width = 120;
            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.camaramarker);
            Bitmap markerIcon = Bitmap.createScaledBitmap(b, width, height, false);
            googleMap.addMarker(new MarkerOptions().position(markerPos).title(quest.getTitle()).icon(BitmapDescriptorFactory.fromBitmap(markerIcon)));
        }
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
        }
    }
}