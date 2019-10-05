package com.example.buslo;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Location extends FragmentActivity implements OnMapReadyCallback {
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private GoogleMap mMap;
    double lat, lon;
    String busno;
    BitmapDescriptor bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Bundle bundle = getIntent().getExtras();
        lat= Double.parseDouble(bundle.getString("lat"));
        lon = Double.parseDouble(bundle.getString("lon"));
        busno = bundle.getString("busno");
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Bus").child(busno);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String x = dataSnapshot.child("lat").getValue().toString();
                String y = dataSnapshot.child("lon").getValue().toString();
                updateMarker(Double.parseDouble(x), Double.parseDouble(y));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        bitmap = BitmapDescriptorFactory.fromResource(R.drawable.rides);


    }

    public void updateMarker(double lat, double lon){
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).snippet("Current Position of Bus").icon(bitmap));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lon)));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(13));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, lon);

        mMap.addMarker(new MarkerOptions().position(sydney).snippet("Current Position of bus").icon(bitmap));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(13));
    }
}
