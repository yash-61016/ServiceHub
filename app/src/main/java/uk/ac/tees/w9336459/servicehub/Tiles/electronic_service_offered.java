package uk.ac.tees.w9336459.servicehub.Tiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import uk.ac.tees.w9336459.servicehub.Requests;
import uk.ac.tees.w9336459.servicehub.U_MainScreen;

import static android.view.View.GONE;

public class electronic_service_offered extends AppCompatActivity {

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
        setContentView(R.layout.activity_electronic_service_offered);

        titleimage = findViewById(R.id.TitleImage);
        title = findViewById(R.id.U_ES_title);

        mSP = new ArrayList<>();
       // mResultList = findViewById(R.id.U_ES_recyclerView);
      //  mResultList.setHasFixedSize(true);
      //  mResultList.setLayoutManager(new LinearLayoutManager(this));


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            int resId = bundle.getInt("resId1");
            titleimage.setBackgroundResource(resId);
            String titleId = getIntent().getStringExtra("title");
            title.setText(titleId);
            if(title.getText().toString().equals("Laptop and Desktop Repairing")) {

                mResultList = (RecyclerView) findViewById(R.id.U_ES_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
               // mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Details");
                Toast.makeText(this, "Select the providers1", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Laptop and Desktop Repairing");
                mResultList.setVisibility(View.VISIBLE);

            }else if(title.getText().toString().equals("Television Repairing")) {

                mResultList = (RecyclerView) findViewById(R.id.U_ES_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
               // mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Details");
                Toast.makeText(this, "Select the providers2", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Television Repairing");
                mResultList.setVisibility(View.VISIBLE);

            }else if(title.getText().toString().equals("Music System Repairing")) {

                mResultList = (RecyclerView) findViewById(R.id.U_ES_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
               // mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Details");
                Toast.makeText(this, "Select the providers3", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Music System Repairing");
                mResultList.setVisibility(View.VISIBLE);

            }else if(title.getText().toString().equals("Smartphone Repairing")) {

                mResultList = (RecyclerView) findViewById(R.id.U_ES_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
                //mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Details");
                Toast.makeText(this, "Select the providers4", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Smartphone Repairing");
                mResultList.setVisibility(View.VISIBLE);
            }
        }


    }


    public void ChangeRecyclerView(String service){


        if(service.equals("Laptop and Desktop Repairing")){
             readUsers("Laptop and Desktop Repairing");
        }else if(service.equals("Television Repairing")){
            readUsers("Television Repairing");
        }else if(service.equals("Music System Repairing")){
            readUsers("Music System Repairing");
        }else if(service.equals("Smartphone Repairing")){
            readUsers("Smartphone Repairing");
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
                useradapter = new ListAdapter(electronic_service_offered.this,mSP);
                mResultList.setAdapter(useradapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        commandsRef.addListenerForSingleValueEvent(eventListener);
    }

    public void sentrequestlist(View view){

        Intent intent = new Intent(electronic_service_offered.this, Requests.class);
        startActivity(intent);
    }


}