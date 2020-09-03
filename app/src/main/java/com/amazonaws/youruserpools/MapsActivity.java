package com.amazonaws.youruserpools;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<LatLng> arrayList=new ArrayList<LatLng>();
    LatLng swargate=new LatLng(18.5018,73.8636);
    LatLng shivajiNagar=new LatLng(18.5314,73.8446);
    LatLng bibwewadi=new LatLng(18.4690,73.8641);
    LatLng katraj=new LatLng(18.4529,73.8652);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        ArrayList<LatLng> arrayList1 = getIntent().getParcelableArrayListExtra("latsNlons");
        Log.i("arrayList1", "the lats ans lons are: " +arrayList1);

       // arrayList.add(swargate);
        //arrayList.add(shivajiNagar);
        //arrayList.add(bibwewadi);
        //arrayList.add(katraj);
        assert arrayList1 != null;
        arrayList.addAll(arrayList1);


        Log.i("arrayList", "the lats ans lons that added: " +arrayList);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for(int i=0;i<arrayList.size();i++)
        {
            mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title("Marker").icon(BitmapDescriptorFactory.fromResource(R.drawable.bbus3)));
            Log.i("location", "onMapReady: " +arrayList.get(i));
        }
       mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(arrayList.get(0), 12.0f));

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
