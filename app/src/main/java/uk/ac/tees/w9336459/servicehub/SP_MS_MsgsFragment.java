package uk.ac.tees.w9336459.servicehub;

import android.os.Build;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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

import uk.ac.tees.w9336459.servicehub.Adapter.ServiceProviderAdapter;
import uk.ac.tees.w9336459.servicehub.Model.ServiceProvider;


public class SP_MS_MsgsFragment extends Fragment {
    private RecyclerView recyclerView;

    private ServiceProviderAdapter serviceProviderAdapter;
    private List<ServiceProvider> mServiceProvider;

    EditText search_serviceProvider;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_s_p__m_s__msgs, container, false);
        recyclerView = view.findViewById(R.id.SP_MS_RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mServiceProvider = new ArrayList<>();

        readServiceProviders();

        return view;
    }

    private void readServiceProviders() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ServiceProvider").child("Details");

       reference.addValueEventListener(new ValueEventListener() {
           @RequiresApi(api = Build.VERSION_CODES.M)
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               mServiceProvider.clear();
               for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                   ServiceProvider serviceProvider = snapshot.getValue(ServiceProvider.class);

                   assert serviceProvider != null;
                   assert firebaseUser != null;
                   if(!serviceProvider.getId().equals(firebaseUser.getUid())){
                       mServiceProvider.add(serviceProvider);
                   }


                   serviceProviderAdapter = new ServiceProviderAdapter(getContext(), mServiceProvider);
                   recyclerView.setAdapter(serviceProviderAdapter);
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
    }
}