package uk.ac.tees.w9336459.servicehub;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import uk.ac.tees.w9336459.servicehub.Tiles.*;

public class MapDirections extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationClickListener,
        GoogleMap.OnMyLocationButtonClickListener {
    private GoogleMap mMap;
    Button getDirection;
    public static Double cusLat, cusLng;
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_directions);
        getDirection = findViewById(R.id.btnGetDirection);
        Bundle b = getIntent().getExtras();
        final double spLat = b.getDouble("spLat");
        final double spLng = b.getDouble("spLng");
        getDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + spLat + "," + spLng);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String EmailChildID = U_MainScreen.decodeUserEmail(user.getEmail());
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child("Details").child(EmailChildID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    cusLat = dataSnapshot.child("latitude").getValue(Double.class);
                    cusLng = dataSnapshot.child("longitude").getValue(Double.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        fetchLastLocation();
    }

    private void fetchLastLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapDirections.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Bundle b = getIntent().getExtras();
        double spLat = b.getDouble("spLat");
        double spLng = b.getDouble("spLng");

        LatLng spLocation = new LatLng(spLat, spLng);
        LatLng cusLocation = new LatLng(cusLat, cusLng);
        mMap = googleMap;
        Log.d("mylog", "Added Markers");
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(spLocation)
                .title("Service Provier will reach here");
        googleMap.addMarker(markerOptions1).showInfoWindow();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spLocation, 16));

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(cusLocation)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        googleMap.addMarker(markerOptions);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLastLocation();
                }
                break;
        }}


    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }
}
