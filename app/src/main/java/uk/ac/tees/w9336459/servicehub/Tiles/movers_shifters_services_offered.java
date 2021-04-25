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

public class movers_shifters_services_offered extends AppCompatActivity {

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
        setContentView(R.layout.activity_movers_shifters_services_offered);
        titleimage= findViewById(R.id.U_MAS_titleImg);
        title = findViewById(R.id.U_MAS_title);
        mSP = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("resId");
            titleimage.setBackgroundResource(resId);
            String titleId = getIntent().getStringExtra("title");
            title.setText(titleId);
            if (title.getText().toString().equals("Home Shifting")) {
                mResultList = (RecyclerView) findViewById(R.id.U_MAS_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
                //mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Profile");
                Toast.makeText(this, "Select the providers1", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Home Shifting");
                mResultList.setVisibility(View.VISIBLE);
            } else if (title.getText().toString().equals("Home Delivery")) {
                mResultList = (RecyclerView) findViewById(R.id.U_MAS_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
                //mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Profile");
                Toast.makeText(this, "Select the providers2", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Home Delivery");
                mResultList.setVisibility(View.VISIBLE);
            }
        }

    }

    public void ChangeRecyclerView(String service){


        if(service.equals("Home Shifting")){
            readUsers("Home Shifting");
        }else if(service.equals("Home Shifting")){
            readUsers("Home Shifting");
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
                useradapter = new ListAdapter(movers_shifters_services_offered.this,mSP);
                mResultList.setAdapter(useradapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        commandsRef.addListenerForSingleValueEvent(eventListener);
    }
}