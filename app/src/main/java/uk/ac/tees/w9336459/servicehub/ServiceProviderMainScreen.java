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

    Button active,pending,completed;
    public static Double spLat, spLng;
    CircleImageView profile_image;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_main_screen);
        profile_image = findViewById(R.id.SP_MS_profile_image);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Details").child(decodeUserEmail(user.getEmail()));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ServiceProviders2 c = dataSnapshot.getValue(ServiceProviders2.class);
                spLat = c.getLatitude();
                spLng = c.getLongitude();
                b = new Bundle();

                b.putString("Name",c.getFirstname() + c.getLastname());
                b.putString("Number",c.getPhonenum());
                b.putString("Email",c.getEmailid());
                b.putString("pic",c.getProfilepicture());
                b.putString("Address",c.getAddress());
                Picasso.get().load(c.getProfilepicture()).into(profile_image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        BottomNavigationView navView = findViewById(R.id.nav_view_sp);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_sp);
        NavigationUI.setupWithNavController(navView, navController);

        profile_image.setOnClickListener((v)->{
            Intent i  = new Intent(this, ServiceProvider_details.class);
            i.putExtras(b);
            startActivity(i);
        });

    }
    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to exit ?");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                moveTaskToBack(true);
            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
            }

        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void logout(View v){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you want to Logout ?");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(ServiceProviderMainScreen.this,MainActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

            }
        });
        builder.setPositiveButton("No",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface,int i){

                dialogInterface.cancel();
            }

        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }

    public void contact(View v){
        Intent intent=new Intent(this,contact_us.class);
        startActivity(intent);

    }
    public void reset(View v)
    {
        Intent intent=new Intent(this,ResetPassword.class);
        startActivity(intent);

    }
    public void changelocation(View v)
    {
        Intent intent = new Intent(this,asklocationSP.class);
        startActivity(intent);
    }
    public void onServiceClick(View v){
        Button b = (Button)v;
        String buttontext = b.getText().toString();

        Intent intent = new Intent(this,RequestStatus.class);
        intent.putExtra("buttontext",buttontext);
        startActivity(intent);
    }
    public void servicehistory(View v){
        Intent intent = new Intent(this,RequestStatus.class);
        intent.putExtra("buttontext","Completed Requests");
        startActivity(intent);

    }

    public static String decodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

}