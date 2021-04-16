package uk.ac.tees.w9336459.servicehub;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.database.CursorJoiner;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.io.Serializable;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.ac.tees.w9336459.servicehub.Model.Users;
import uk.ac.tees.w9336459.servicehub.Tiles.electronic_tile;
import uk.ac.tees.w9336459.servicehub.Tiles.health_service_tile;
import uk.ac.tees.w9336459.servicehub.Tiles.home_services_tile;
import uk.ac.tees.w9336459.servicehub.Tiles.mover_shifters_service_tile;
import uk.ac.tees.w9336459.servicehub.Tiles.party_event_service_tile;
import uk.ac.tees.w9336459.servicehub.Tiles.personal_grooming_service_tile;

public class U_MainScreen extends AppCompatActivity {

    ScrollView sc;
    LinearLayout layout;
    TextView Name, title;
    Button Er_bt, hs_bt, hos_bt, mbt, p_and_ebt, ps_bt, ms_bt;
    EditText searchtext;
    ImageButton mSearchBtn;
    RecyclerView mResultList;
    static private CircleImageView profileimageview;
    private DatabaseReference mServiceProviderDatabse, mref;
    private FirebaseAuth mAuth;
    private Query firebasequery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u__main_screen);


        mServiceProviderDatabse = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("ServiceProviders").child("Profile");

        sc = findViewById(R.id.Scroll);
        sc.setVerticalScrollBarEnabled(true);
        layout = findViewById(R.id.layout_grid);
        Name = findViewById(R.id.U_Ms_name);
        title = findViewById(R.id.U_Ms_title);


        Er_bt = findViewById(R.id.U_Ms_ElectronicRepairs);
        hs_bt = findViewById(R.id.U_Ms_Health);
        hos_bt = findViewById(R.id.U_Ms_houseServices);
        mbt = findViewById(R.id.U_Ms_MoversandShifters);
        p_and_ebt = findViewById(R.id.U_Ms_PartyandEvents);
        ps_bt = findViewById(R.id.U_Ms_PersonalGrooming);

        ms_bt = findViewById(R.id.U_Ms_Menubt);
        //profile_btn = findViewById(R.id.profile);
        profileimageview = findViewById(R.id.profile);

        ms_bt.setOnClickListener((v) -> {
            Intent a = new Intent(U_MainScreen.this, menubtn.class);
            startActivity(a);
            finish();
        });

        hs_bt.setOnClickListener((v) -> {
            Intent a = new Intent(U_MainScreen.this, health_service_tile.class);
            startActivity(a);
        });
        Er_bt.setOnClickListener((v) -> {
            Intent a = new Intent(U_MainScreen.this, electronic_tile.class);
            startActivity(a);
        });
        hos_bt.setOnClickListener((v) -> {
            Intent a = new Intent(U_MainScreen.this, home_services_tile.class);
            startActivity(a);
        });
        mbt.setOnClickListener((v) -> {
            Intent a = new Intent(U_MainScreen.this, mover_shifters_service_tile.class);
            startActivity(a);
        });
        p_and_ebt.setOnClickListener((v) -> {
            Intent a = new Intent(U_MainScreen.this, party_event_service_tile.class);
            startActivity(a);
        });
        ps_bt.setOnClickListener((v) -> {
            Intent a = new Intent(U_MainScreen.this, personal_grooming_service_tile.class);
            startActivity(a);
        });

        profileimageview.setOnClickListener((V) -> {
            Intent Profile = new Intent(U_MainScreen.this, profile.class);
            startActivity(Profile);
        });



        searchtext = (EditText)findViewById(R.id.U_Ms_search);
        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);
        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
        mSearchBtn.setOnClickListener(view -> {

            mResultList.setVisibility(View.VISIBLE);
            sc.setVisibility(View.INVISIBLE);
            firebaseUserSearch(searchtext.getText().toString().toUpperCase());


        });


        /***
         * Code for the title name
         */

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String EmailChildID = decodeUserEmail(user.getEmail());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child("Details").child(EmailChildID);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Read from the database
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    String value = dataSnapshot.child("name").getValue(String.class);
                    Name.setText(value);
                    if (dataSnapshot.hasChild("image")) {
                        String image = dataSnapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profileimageview);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       // mServiceProviderDatabse = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Profile");

    }




    static String decodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(U_MainScreen.this);
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


    // View Holder Class
    public void firebaseUserSearch(String searchText) {
        Toast.makeText(U_MainScreen.this, "Started Search", Toast.LENGTH_LONG).show();
      //  Query firebasequery = mServiceProviderDatabse.orderByChild("name").startAt(searchText).endAt(searchText+"\uf8ff");

        Query fbq = mServiceProviderDatabse.orderByChild("skills").startAt(searchText).endAt(searchText+'\uf8ff');

            FirebaseRecyclerAdapter<Users, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(

                    Users.class,
                    R.layout.list_layout,
                    UsersViewHolder.class,
                    fbq

            ) {
                @Override
                protected void populateViewHolder(UsersViewHolder usersViewHolder, Users users, int i) {

                    usersViewHolder.setDetails(users.getName(), users.getSkills(), users.getProfilepicture());
                }
            };

            mResultList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDetails( String userName, String userStatus, String userImage) {

            TextView user_name = (TextView) mView.findViewById(R.id.name_text);
            TextView user_skills = (TextView) mView.findViewById(R.id.skills_text);
            CircleImageView user_image = mView.findViewById(R.id.profile_picture);


            user_name.setText(userName);
            user_skills.setText(userStatus);

            Picasso.get().load(userImage).into(user_image);

        }

    }
}