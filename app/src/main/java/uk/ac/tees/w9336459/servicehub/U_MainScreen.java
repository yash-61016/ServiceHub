package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import  uk.ac.tees.w9336459.servicehub.Tiles.electronic_tile;
import uk.ac.tees.w9336459.servicehub.Tiles.health_service_tile;
import uk.ac.tees.w9336459.servicehub.Tiles.home_services_tile;
import uk.ac.tees.w9336459.servicehub.Tiles.mover_shifters_service_tile;
import uk.ac.tees.w9336459.servicehub.Tiles.party_event_service_tile;
import uk.ac.tees.w9336459.servicehub.Tiles.personal_grooming_service_tile;

public class U_MainScreen extends AppCompatActivity {

    ScrollView sc;
    LinearLayout layout;
    TextView Name, title;
    Button Er_bt , hs_bt, hos_bt, mbt, p_and_ebt,ps_bt , ms_bt;
    ImageView profile_btn;

    private EditText mSearchField;
    private ImageButton mSearchBtn;

    private RecyclerView mResultList;

    private  FirebaseDatabase database;
    private DatabaseReference mUserDatabase ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u__main_screen);

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
        profile_btn = findViewById(R.id.profile);


        ms_bt.setOnClickListener((v)->{
            Intent a = new Intent(U_MainScreen.this,menubtn.class);
            startActivity(a);
            finish();
        });

        hs_bt.setOnClickListener((v)->{
            Intent a = new Intent(U_MainScreen.this, health_service_tile.class);
            startActivity(a);
        });
        Er_bt.setOnClickListener((v)->{
            Intent a = new Intent(U_MainScreen.this,electronic_tile.class);
            startActivity(a);
        });
        hos_bt.setOnClickListener((v)->{
            Intent a = new Intent(U_MainScreen.this, home_services_tile.class);
            startActivity(a);
        });
        mbt.setOnClickListener((v)->{
            Intent a = new Intent(U_MainScreen.this, mover_shifters_service_tile.class);
            startActivity(a);
        });
        p_and_ebt.setOnClickListener((v)->{
            Intent a = new Intent(U_MainScreen.this, party_event_service_tile.class);
            startActivity(a);
        });
        ps_bt.setOnClickListener((v)->{
            Intent a = new Intent(U_MainScreen.this, personal_grooming_service_tile.class);
            startActivity(a);
        });

        profile_btn.setOnClickListener((V)->{
            Intent i = new Intent(U_MainScreen.this, profile.class);
            startActivity(i);
        });

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Users");


        mSearchField = (EditText) findViewById(R.id.U_Ms_search);
        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);

        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);


            }
        });
        mSearchField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                layout.setVisibility(View.GONE);
                Name.setVisibility(View.GONE);
                title.setVisibility(View.GONE);
            }
        });

    }

    private void firebaseUserSearch(String searchText) {

        Toast.makeText(U_MainScreen.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<services_serviceproviders, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<services_serviceproviders, UsersViewHolder>(

                services_serviceproviders.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                firebaseSearchQuery


        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder usersViewHolder, services_serviceproviders services_serviceproviders, int i) {
                usersViewHolder.setDetails(getApplicationContext(), services_serviceproviders.getName(), services_serviceproviders.getStatus(), services_serviceproviders.getImage());
            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }


    // View Holder Class

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;


        }

        public void setDetails(Context ctx, String userName, String userStatus, String userImage){

            TextView user_name = (TextView) mView.findViewById(R.id.name_text);
            TextView user_status = (TextView) mView.findViewById(R.id.status_text);
            ImageView user_image = (ImageView) mView.findViewById(R.id.profile_image);


            user_name.setText(userName);
            user_status.setText(userStatus);


            Glide.with(ctx).load(userImage).into(user_image);


        }

    }
}