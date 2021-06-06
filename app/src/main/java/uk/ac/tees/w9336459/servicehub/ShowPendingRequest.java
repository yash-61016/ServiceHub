package uk.ac.tees.w9336459.servicehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import uk.ac.tees.w9336459.servicehub.Model.RequestBox;
import uk.ac.tees.w9336459.servicehub.Model.RequestList;

public class ShowPendingRequest extends AppCompatActivity {

    CircleImageView dp;
    private LinearLayout call, message, directions;
    TextView name;
    TextView job,time,date,des;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    Button accept,decline;
    String userid;
    List<RequestBox> mchat;
    String requestid2="", requestid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pending_request);

        name = findViewById(R.id.chat_name);
        job = findViewById(R.id.chat_job);
        dp = findViewById(R.id.chat_dp);

        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
        des = findViewById(R.id.description);

        accept = findViewById(R.id.accept_btn);
        decline = findViewById(R.id.decline_btn);

        call = findViewById(R.id.call);
        message = findViewById(R.id.message);
        directions = findViewById(R.id.directions);




        userid = getIntent().getStringExtra("userid");
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
                readMessage(firebaseUser.getEmail(),userid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("RequestList").child((ServiceProviderMainScreen.decodeUserEmail(firebaseUser.getEmail())));
        databaseReference.child(ServiceProviderMainScreen.decodeUserEmail(userid)).orderByChild("requestid");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                    requestid2 = childSnapshot.child("requestid").getValue(String.class);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("RequestList").child((ServiceProviderMainScreen.decodeUserEmail(firebaseUser.getEmail())));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    RequestList requestList = dataSnapshot1.getValue(RequestList.class);
                    if(requestList.getStatus().equals("active")){
                        Toast.makeText(ShowPendingRequest.this,"You already have an active request",Toast.LENGTH_SHORT).show();
//                        finish();
                        Toast.makeText(ShowPendingRequest.this, "id"+requestid2, Toast.LENGTH_SHORT).show();
                        accept.setClickable(false);
                        accept.setBackgroundColor(Color.GRAY);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + getIntent().getStringExtra("phone")));
                startActivity(intent);

            }});

        directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = getIntent().getExtras();
                Intent intent = new Intent(ShowPendingRequest.this, DirectionsSp.class);
                intent.putExtras(b);
                startActivity(intent);
            }});


        databaseReference = FirebaseDatabase.getInstance().getReference("Requests").child(requestid2);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                    String d  = childSnapshot.child("date").getValue(String.class);
                    String t  = childSnapshot.child("time").getValue(String.class);
                    String desc  = childSnapshot.child("description").getValue(String.class);
                    date.setText(d);
                    time.setText(t);
                    des.setText(desc);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        accept.setOnClickListener(v -> {
/*
            DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("RequestList");
            requestid = databaseReference2.child(userid).child("requestid").toString();*/

            databaseReference = FirebaseDatabase.getInstance().getReference("Requests");
            databaseReference.child(requestid2).child("status").setValue("active");

            databaseReference = FirebaseDatabase.getInstance().getReference("RequestList");
            databaseReference.child(ServiceProviderMainScreen.decodeUserEmail(userid))
                    .child(ServiceProviderMainScreen.decodeUserEmail(firebaseUser.getEmail())).child("status").setValue("active");

            databaseReference = FirebaseDatabase.getInstance().getReference("RequestList");
            databaseReference.child(ServiceProviderMainScreen.decodeUserEmail(firebaseUser.getEmail())).child(ServiceProviderMainScreen.decodeUserEmail(userid)).child("status").setValue("active");

            finish();
        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("Requests");
                databaseReference.child(requestid2)
                        .child("status").setValue("declined");

                databaseReference = FirebaseDatabase.getInstance().getReference("RequestList");
                databaseReference.child(ServiceProviderMainScreen.decodeUserEmail(userid))
                        .child(ServiceProviderMainScreen.decodeUserEmail(firebaseUser.getEmail())).child("status").setValue("declined");

                databaseReference = FirebaseDatabase.getInstance().getReference("RequestList");
                databaseReference.child(firebaseUser.getUid())
                        .child(ServiceProviderMainScreen.decodeUserEmail(userid)).child("status").setValue("declined");

                finish();
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
                    if(requestBox.getReceiver().equals(myid) && requestBox.getSender().equals(userid) ||
                            requestBox.getReceiver().equals(userid) && requestBox.getSender().equals(myid) &&
                                requestBox.getStatus().equals("pending")){
                        date.setText(requestBox.getDate());
                        time.setText(requestBox.getTime());
                        des.setText(requestBox.getDescription());
                        requestid = requestBox.getId();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
