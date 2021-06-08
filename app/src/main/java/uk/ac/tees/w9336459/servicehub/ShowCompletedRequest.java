package uk.ac.tees.w9336459.servicehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

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

public class ShowCompletedRequest extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_completed_request);

        name = findViewById(R.id.chat_name);
        job = findViewById(R.id.chat_job);
        dp = findViewById(R.id.chat_dp);

        recyclerView = findViewById(R.id.message_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        userid = getIntent().getStringExtra("userid");
        type = getIntent().getStringExtra("type");

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child("Details").child(ServiceProviderMainScreen.decodeUserEmail(userid));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Customers s = dataSnapshot.getValue(Customers.class);
                String mname = s.getFirstname() + " " + s.getLastname();
                name.setText(mname);
                job.setText(s.getPhonenum());
                Picasso.get().load(s.getImage()).into(dp);
                readMessage(firebaseUser.getUid(),userid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void readMessage(final String myid, final String userid){
        mchat = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Requests");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mchat.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    RequestBox requestBox = snapshot.getValue(RequestBox.class);
                    if(requestBox.getReceiver().equals(ServiceProviderMainScreen.decodeUserEmail(myid)) && requestBox.getSender().equals(ServiceProviderMainScreen.decodeUserEmail(userid)) ||
                            requestBox.getReceiver().equals(ServiceProviderMainScreen.decodeUserEmail(userid)) && requestBox.getSender().equals(ServiceProviderMainScreen.decodeUserEmail(myid))){
                        mchat.add(requestBox);
                    }
                    requestAdapter = new RequestAdapter(ShowCompletedRequest.this,mchat,null);
                    recyclerView.setAdapter(requestAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
