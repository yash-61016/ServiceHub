package uk.ac.tees.w9336459.servicehub;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

public class SP_MS_ActiveJobsFragment extends Fragment {

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
    public static Double spLat, spLng;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = (View) inflater.inflate(R.layout.fragment_s_p__m_s_a_j_fragment, container,false);

        //Do all things which you want to implement //enter code here
        Bundle b = new Bundle();
        check  = b.getString("buttontext");
        heading = heading.findViewById(R.id.request_text);
        heading.setText(check);
        recyclerView = recyclerView.findViewById(R.id.request_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        if(check.equals("Active Request")){status = "active";}
        if(check.equals("Pending Requests")){status = "pending";}
       // if(check.equals("Completed Requests")){status = "completed";}

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        userList = new ArrayList<>();

        database = FirebaseDatabase.getInstance().getReference("Requestlist").child(fuser.getUid());
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
        return view;
    }

    private void chatList() {
        musers = new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference("Customers");


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                musers.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Customers c = dataSnapshot1.getValue(Customers.class);
                    for (RequestList chatlist : userList) {
                        if (c.getId().equals(chatlist.getId()) && chatlist.getStatus().equals(status)) {
                            musers.add(c);
                        }
                    }
                    pbar.setVisibility(GONE);
                }

                listAdapter = new ShowRequestAdapter(getActivity().getApplicationContext(), musers,status);
                recyclerView.setAdapter(listAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }
}
