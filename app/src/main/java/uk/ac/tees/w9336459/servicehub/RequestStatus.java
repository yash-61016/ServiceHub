package uk.ac.tees.w9336459.servicehub;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
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

import java.util.ArrayList;
import java.util.List;


import uk.ac.tees.w9336459.servicehub.Model.RequestList;
import uk.ac.tees.w9336459.servicehub.Model.ShowRequestAdapter;

import static android.view.View.GONE;

    public class RequestStatus extends AppCompatActivity {

        TextView heading;
        private RecyclerView recyclerView;
        ProgressBar pbar;
        private ShowRequestAdapter listAdapter;
        private List<Customers> musers;

        FirebaseUser fuser;
        DatabaseReference database;
        private List<RequestList> userList;
        String check;
        String status;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_request_status);

            pbar = findViewById(R.id.progressBar);
            heading = findViewById(R.id.request_text);
            check = getIntent().getStringExtra("buttontext");
            heading.setText(check);
            recyclerView = (RecyclerView) findViewById(R.id.request_list);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            if(check.equals("Active Request")){status = "active";}
            if(check.equals("Pending Requests")){status = "pending";}
            if(check.equals("Completed Requests")){status = "completed";}

            fuser = FirebaseAuth.getInstance().getCurrentUser();

            userList = new ArrayList<>();

            database = FirebaseDatabase.getInstance().getReference("RequestList").child(ServiceProviderMainScreen.decodeUserEmail(fuser.getEmail()));
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    userList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        RequestList chatlist = snapshot.getValue(RequestList.class);
                        userList.add(chatlist);
                    }
                    chatList();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        private void chatList() {
            musers = new ArrayList<>();

            database = FirebaseDatabase.getInstance().getReference("Users").child("Details");
            database.orderByChild("emailid").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    musers.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Customers c = dataSnapshot1.getValue(Customers.class);
                        for (RequestList chatlist : userList) {
                            String id = ServiceProviderMainScreen.decodeUserEmail(chatlist.getId());
                            if ((c.getEmailid()).equals(id)
                                    && chatlist.getStatus().equals(status)) {
                                musers.add(c);
                            }
                        }
                       // pbar.setVisibility(GONE);
                    }

                    listAdapter = new ShowRequestAdapter(RequestStatus.this, musers,status);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(listAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
}
