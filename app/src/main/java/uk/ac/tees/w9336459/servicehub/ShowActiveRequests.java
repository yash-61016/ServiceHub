package uk.ac.tees.w9336459.servicehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
import uk.ac.tees.w9336459.servicehub.Model.RequestBox;

public class ShowActiveRequests extends AppCompatActivity {

    CircleImageView dp;
    TextView name;
    private LinearLayout call, message, directions;
    TextView job,time,date,des;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    String userid,requestid,requestid2="";
    List<RequestBox> mchat;
    Button complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_active_requests);

        name = findViewById(R.id.chat_name);
        job = findViewById(R.id.chat_job);
        dp = findViewById(R.id.chat_dp);

        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
        des = findViewById(R.id.description);
        complete = findViewById(R.id.endtask);

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
                //readMessage(firebaseUser.getUid(),userid);
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
                Intent intent = new Intent(ShowActiveRequests.this, DirectionsSp.class);
                intent.putExtras(b);
                startActivity(intent);

            }});

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowActiveRequests.this,Chat.class);
                intent.putExtra("userid",getIntent().getStringExtra("userid"));
                intent.putExtra("type","Users");
                startActivity(intent);

            }});
        databaseReference = FirebaseDatabase.getInstance().getReference("Requests").child(requestid2);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    String d = childSnapshot.child("date").getValue(String.class);
                    String t = childSnapshot.child("time").getValue(String.class);
                    String desc = childSnapshot.child("description").getValue(String.class);
                    date.setText(d);
                    time.setText(t);
                    des.setText(desc);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("Requests");
                databaseReference.child(requestid2)
                        .child("status").setValue("completed");

                databaseReference = FirebaseDatabase.getInstance().getReference("RequestList");
                databaseReference.child(ServiceProviderMainScreen.decodeUserEmail(userid))
                        .child(ServiceProviderMainScreen.decodeUserEmail(firebaseUser.getEmail())).child("status").setValue("completed");

                databaseReference = FirebaseDatabase.getInstance().getReference("RequestList");
                databaseReference.child(ServiceProviderMainScreen.decodeUserEmail(firebaseUser.getEmail()))
                        .child(ServiceProviderMainScreen.decodeUserEmail(userid)).child("status").setValue("completed");

                complete.setClickable(false);
                complete.setText("Completed");
                complete.setBackgroundColor(Color.GRAY);

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
                    if(requestBox.getReceiver().equals(ServiceProviderMainScreen.decodeUserEmail(myid)) && requestBox.getSender().equals(ServiceProviderMainScreen.decodeUserEmail(userid)) ||
                            requestBox.getReceiver().equals(ServiceProviderMainScreen.decodeUserEmail(userid)) && requestBox.getSender().equals(ServiceProviderMainScreen.decodeUserEmail(myid)) &&
                                    requestBox.getStatus().equals("active")){
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