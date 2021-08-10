package uk.ac.tees.w9336459.servicehub;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.ac.tees.w9336459.servicehub.Model.ServiceProviders2;
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
    String imageref ;

    private AutoCompleteTextView txtSearch;
    private ListView listData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u__main_screen);


        mServiceProviderDatabse = FirebaseDatabase.getInstance().getReference().child("ServiceProviders").child("Details");

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
            a.putExtra("profilepic",imageref);
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

            if(searchtext.getText().toString() == null){
                mResultList.setVisibility(View.INVISIBLE);
                sc.setVisibility(View.VISIBLE);
            }else {
                mResultList.setVisibility(View.VISIBLE);
                sc.setVisibility(View.INVISIBLE);
                firebaseUserSearch(searchtext.getText().toString().toUpperCase());
            }


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
                    String value = dataSnapshot.child("firstname").getValue(String.class);
                    Name.setText("Hey, "+value);
                    if(dataSnapshot.child("image").getValue().toString().equals("null")){
                        Picasso.get().load(R.drawable.avatar).into(profileimageview);
                    }
                    else  {
                        String image = dataSnapshot.child("image").getValue().toString();
                        imageref = image;
                        Picasso.get().load(image).into(profileimageview);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void populatesearch() {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.exists()) {
                ArrayList<String> skillname=new ArrayList<>();
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }

    public static String decodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

    @Override
    public void onBackPressed() {


        if(searchtext.isFocused() ) {
            startActivity(new Intent(this, U_MainScreen.class));
        }else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(U_MainScreen.this);
            builder.setMessage("Do you want to exit ?");
            builder.setCancelable(true);
            builder.setNegativeButton("Yes", (dialogInterface, i) -> moveTaskToBack(true));
            builder.setPositiveButton("No", (dialogInterface, i) -> dialogInterface.cancel());
            builder.setInverseBackgroundForced(true);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }

    /**
     *
     * firebase search
     * @param searchText
     */

    // View Holder Class
    public void firebaseUserSearch(String searchText) {
        Toast.makeText(U_MainScreen.this, "Started Search", Toast.LENGTH_LONG).show();
      //  Query firebasequery = mServiceProviderDatabse.orderByChild("name").startAt(searchText).endAt(searchText+"\uf8ff");

        Query fbq = mServiceProviderDatabse.orderByChild("skills").startAt(searchText).endAt(searchText+'\uf8ff');

            FirebaseRecyclerAdapter<ServiceProviders2, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ServiceProviders2, UsersViewHolder>(

                    ServiceProviders2.class,
                    R.layout.list_layout,
                    UsersViewHolder.class,
                    fbq

            ) {
                @Override
                protected void populateViewHolder(UsersViewHolder usersViewHolder, ServiceProviders2 users, int i) {

                    usersViewHolder.setDetails(users.getFirstname(),users.getLastname(), users.getSkills(), users.getProfilepicture());

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

        public void setDetails( String fName,String lname, String skills, String Image) {

            TextView sp_f_name = (TextView) mView.findViewById(R.id.name_text1);
           // TextView sp_l_name = (TextView) mView.findViewById(R.id.name_text2);
            TextView sp_skills = (TextView) mView.findViewById(R.id.skills_text);
            CircleImageView sp_image = mView.findViewById(R.id.profile_picture);


            sp_skills.setText(skills);
            sp_f_name.setText(fName  +" "+ lname);
            Picasso.get().load(Image).into(sp_image);

        }

    }




}