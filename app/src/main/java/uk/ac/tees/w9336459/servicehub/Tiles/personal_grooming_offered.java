package uk.ac.tees.w9336459.servicehub.Tiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.ac.tees.w9336459.servicehub.Model.ListAdapter;
import uk.ac.tees.w9336459.servicehub.Model.ServiceProviders2;
import uk.ac.tees.w9336459.servicehub.R;
import uk.ac.tees.w9336459.servicehub.U_MainScreen;

public class personal_grooming_offered extends AppCompatActivity {

    ImageView titleimage;
    TextView title;
    private DatabaseReference mref;
    private RecyclerView mResultList;
    private ListAdapter useradapter;
    private List<ServiceProviders2> mSP;
    ProgressBar pbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_grooming_offered);
        mSP = new ArrayList<>();
        titleimage= findViewById(R.id.U_PG_titleImg);
        title = findViewById(R.id.U_PG_title);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("resId");
            titleimage.setBackgroundResource(resId);
            String titleId = getIntent().getStringExtra("title");
            title.setText(titleId);
            if (title.getText().toString().equals("Hairdresser")) {
                mResultList = (RecyclerView) findViewById(R.id.U_PG_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
                //mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Profile");
                Toast.makeText(this, "Select the providers1", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Hairdresser");
                mResultList.setVisibility(View.VISIBLE);
            } else if (title.getText().toString().equals("Masseuse")) {
                mResultList = (RecyclerView) findViewById(R.id.U_PG_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
                //mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Profile");
                Toast.makeText(this, "Select the providers2", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Masseuse");
                mResultList.setVisibility(View.VISIBLE);
            } else if (title.getText().toString().equals("Manicure and Pedicure")) {
                mResultList = (RecyclerView) findViewById(R.id.U_PG_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
                //mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Profile");
                Toast.makeText(this, "Select the providers2", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Manicure and Pedicure");
                mResultList.setVisibility(View.VISIBLE);
            }else if (title.getText().toString().equals("Facial and Makeup")) {
                mResultList = (RecyclerView) findViewById(R.id.U_PG_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
                //mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Profile");
                Toast.makeText(this, "Select the providers2", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Facial and Makeup");
                mResultList.setVisibility(View.VISIBLE);
            }
        }
    }

    public void ChangeRecyclerView(String service){


        if(service.equals("Hairdresser")){
            readUsers("Hair");
        }else if(service.equals("Masseuse")){
            readUsers("Masseuse");
        }else if(service.equals("Manicure and Pedicure")){
            readUsers("Manicure and Pedicure");
        }else if(service.equals("Facial and Makeup")){
            readUsers("Facial and Makeup Artist");
        }
    }


    public void readUsers(String services){

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference commandsRef = rootRef.child("ServiceProviders").child("Details");
        ValueEventListener eventListener= new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mSP.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ServiceProviders2 serviceProviders = snapshot.getValue(ServiceProviders2.class);

                    assert serviceProviders!=null;
                    assert firebaseUser!=null;

                    if(services.equals("Hair") &&
                            (serviceProviders.getSkills().equals("Hair Dressing (For Men)") ||
                            serviceProviders.getSkills().equals("Hair Dressing (For Women)") ||
                            serviceProviders.getSkills().equals("Hair Dressing (Unisex)"))){
                        mSP.add(serviceProviders);
                    }
                    else if(services.equals(serviceProviders.getSkills())){
                        mSP.add(serviceProviders);
                        }


                    //pbar.setVisibility(GONE);
                }
                Bundle b = getIntent().getExtras();
                useradapter = new ListAdapter(personal_grooming_offered.this,mSP);
                mResultList.setAdapter(useradapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        commandsRef.addListenerForSingleValueEvent(eventListener);
    }
}