package uk.ac.tees.w9336459.servicehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.ac.tees.w9336459.servicehub.Model.ServiceProviders2;

public class ServiceProviderMainScreen extends AppCompatActivity {

    public static Double spLat, spLng;
    CircleImageView PP ;
    Button Request_active,Request_pending,Request_completed;
    ImageButton menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_main_screen);

        String dp = getIntent().getStringExtra("downloadUrlPP");
        PP = findViewById(R.id.SP_MS_profile_image);
        Picasso.get().load(dp).into(PP);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Details").child(decodeUserEmail(user.getEmail()));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ServiceProviders2 c = dataSnapshot.getValue(ServiceProviders2.class);
                spLat = c.getLatitude();
                spLng = c.getLongitude();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Request_active = findViewById(R.id.request_active);
        Request_completed = findViewById(R.id.request_completed);
        Request_pending = findViewById(R.id.request_pending);

        Request_active.setOnClickListener((v)->{

            Button b = (Button)v;
            String buttontext = b.getText().toString();

            Intent intent = new Intent(ServiceProviderMainScreen.this,RequestStatus.class);
            intent.putExtra("buttontext",buttontext);
            startActivity(intent);
        });
        Request_pending.setOnClickListener((v)->{

            Button b = (Button)v;
            String buttontext = b.getText().toString();

            Intent intent = new Intent(ServiceProviderMainScreen.this,RequestStatus.class);
            intent.putExtra("buttontext",buttontext);
            startActivity(intent);
        });
        Request_completed.setOnClickListener((v)->{

            Button b = (Button)v;
            String buttontext = b.getText().toString();

            Intent intent = new Intent(ServiceProviderMainScreen.this,RequestStatus.class);
            intent.putExtra("buttontext",buttontext);
            startActivity(intent);
        });

        menu = findViewById(R.id.SP_MS_menu);
        menu.setOnClickListener((v)->{
            Intent intent = new Intent(ServiceProviderMainScreen.this,ServiceProvider_Menu.class);
            startActivity(intent);
        });
        PP.setOnClickListener((v)->{
            Intent intent = new Intent(ServiceProviderMainScreen.this,ServiceProvider_details.class);
            startActivity(intent);
        });

    }
    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(ServiceProviderMainScreen.this);
        builder.setMessage("Do you want to exit ?");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", (dialogInterface, i) -> moveTaskToBack(true));
        builder.setPositiveButton("No", (dialogInterface, i) -> dialogInterface.cancel());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public static String decodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

}