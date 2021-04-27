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

public class home_service_offered extends AppCompatActivity {

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
        setContentView(R.layout.activity_home_service_offered);
        titleimage= findViewById(R.id.U_HS_titleImg);
        title = findViewById(R.id.U_HS_title);
        mSP = new ArrayList<>();


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("resId");
            titleimage.setBackgroundResource(resId);
            String titleId = getIntent().getStringExtra("title");
            title.setText(titleId);

            if (title.getText().toString().equals("Interior Designing")) {
                mResultList = (RecyclerView) findViewById(R.id.U_HS_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
                //mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Profile");
                Toast.makeText(this, "Select the providers1", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Interior Designing");
                mResultList.setVisibility(View.VISIBLE);
            } else if (title.getText().toString().equals("Plumbing")) {
                mResultList = (RecyclerView) findViewById(R.id.U_HS_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
                //mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Profile");
                Toast.makeText(this, "Select the providers2", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Plumbing");
                mResultList.setVisibility(View.VISIBLE);
            } else if (title.getText().toString().equals("Painting")) {
                mResultList = (RecyclerView) findViewById(R.id.U_HS_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
                //mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Profile");
                Toast.makeText(this, "Select the providers3", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Painting");
                mResultList.setVisibility(View.VISIBLE);
            }
        }
        }

    public void ChangeRecyclerView(String service){


        if(service.equals("Interior Designing")){
            readUsers("Interior Designing");
        }else if(service.equals("Plumbing")){
            readUsers("Plumbing");
        }else if(service.equals("Painting")){
            readUsers("Painting");
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

                    if(services.equals(serviceProviders.getSkills())){
                        mSP.add(serviceProviders);
                    }
                    //pbar.setVisibility(GONE);
                }
                Bundle b = getIntent().getExtras();
                useradapter = new ListAdapter(home_service_offered.this,mSP);
                mResultList.setAdapter(useradapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        commandsRef.addListenerForSingleValueEvent(eventListener);
    }

}
