package uk.ac.tees.w9336459.servicehub;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.ac.tees.w9336459.servicehub.Model.RequestAdapter;
import uk.ac.tees.w9336459.servicehub.Model.RequestBox;
import uk.ac.tees.w9336459.servicehub.Model.ServiceProviders2;

public class ShowSentRequest extends AppCompatActivity {

    CircleImageView dp;
    TextView name;
    TextView job;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    String userid,type;
    List<RequestBox> mchat;
    RequestAdapter requestAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_request);

        name = findViewById(R.id.chat_name);
        job = findViewById(R.id.chat_job);
        dp = findViewById(R.id.chat_dp);

        recyclerView = findViewById(R.id.message_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        userid = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(type.equals("ServiceProvider")){
            databaseReference = FirebaseDatabase.getInstance().getReference().child("ServiceProviders").child("Details").child(ServiceProviderMainScreen.decodeUserEmail(userid));
        } else {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child("Details").child(ServiceProviderMainScreen.decodeUserEmail(userid));
        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ServiceProviders2 s = dataSnapshot.getValue(ServiceProviders2.class);
                String mname = s.getFirstname() ;
                name.setText(mname);
                job.setText(s.getSkills());
                if(s.getProfilepicture()==null){
                    dp.setImageResource(R.drawable.avatar);
                }
                Picasso.get().load(s.getProfilepicture()).into(dp);
                readMessage(firebaseUser.getEmail(),userid,s.getProfilepicture());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void readMessage(final String myid, final String userid, final String imageurl){
        mchat = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Requests");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mchat.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    RequestBox requestBox = snapshot.getValue(RequestBox.class);
                    if(requestBox.getReceiver().equals(myid) && requestBox.getSender().equals(userid) ||
                            requestBox.getReceiver().equals(userid) && requestBox.getSender().equals(myid)){
                        mchat.add(requestBox);
                    }
                    requestAdapter = new RequestAdapter(ShowSentRequest.this,mchat,imageurl);
                    recyclerView.setAdapter(requestAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
