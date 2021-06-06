package uk.ac.tees.w9336459.servicehub.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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

import uk.ac.tees.w9336459.servicehub.Chatlist;
import uk.ac.tees.w9336459.servicehub.Customers;
import uk.ac.tees.w9336459.servicehub.R;
import uk.ac.tees.w9336459.servicehub.ShowMessageAdapter;

public class DashboardFragmentSP extends Fragment {

    private RecyclerView recyclerView;
    private ShowMessageAdapter listAdapter;
    private List<Customers> musers;

    FirebaseUser fuser;
    DatabaseReference database;

    private List<Chatlist> userList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard_sp, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fuser = FirebaseAuth.getInstance().getCurrentUser();

        userList = new ArrayList<>();

        database = FirebaseDatabase.getInstance().getReference("Chatlist").child(fuser.getUid());
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Chatlist chatlist = snapshot.getValue(Chatlist.class);
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

    private void chatList(){
        musers = new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference("Customers");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                musers.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Customers c = dataSnapshot1.getValue(Customers.class);
                    for(Chatlist chatlist : userList){
                        if(c.getId().equals(chatlist.getId())){
                            musers.add(c);
                        }
                    }

                }
                listAdapter = new ShowMessageAdapter(getContext(),musers);
                recyclerView.setAdapter(listAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}