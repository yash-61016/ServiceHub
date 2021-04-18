package uk.ac.tees.w9336459.servicehub.Tiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.ac.tees.w9336459.servicehub.Model.ServiceProviders;
import uk.ac.tees.w9336459.servicehub.R;

public class movers_shifters_services_offered extends AppCompatActivity {

    ImageView titleimage;
    TextView title;
    private DatabaseReference mref;
    private RecyclerView mResultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movers_shifters_services_offered);
        titleimage= findViewById(R.id.U_MAS_titleImg);
        title = findViewById(R.id.U_MAS_title);

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
                mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Profile");
                Toast.makeText(this, "Select the providers1", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Home Shifting");
                mResultList.setVisibility(View.VISIBLE);
            } else if (title.getText().toString().equals("Home Delivery")) {
                mResultList = (RecyclerView) findViewById(R.id.U_MAS_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
                mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Profile");
                Toast.makeText(this, "Select the providers2", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Home Delivery");
                mResultList.setVisibility(View.VISIBLE);
            }
        }

    }

    private void ChangeRecyclerView(String service) {
        Query fbq = mref.orderByChild("skills").startAt(service).endAt(service+'\uf8ff');

        FirebaseRecyclerAdapter<ServiceProviders, movers_shifters_services_offered.ServiceProviderViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ServiceProviders, movers_shifters_services_offered.ServiceProviderViewHolder>(

                ServiceProviders.class,
                R.layout.list_layout,
                movers_shifters_services_offered.ServiceProviderViewHolder.class,
                fbq

        ) {
            @Override
            protected void populateViewHolder(movers_shifters_services_offered.ServiceProviderViewHolder usersViewHolder, ServiceProviders users, int i) {

                usersViewHolder.setDetails(users.getName(), users.getSkills(), users.getProfilepicture());
            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);
    }
    public static class ServiceProviderViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public ServiceProviderViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDetails(String userName, String userskills, String userImage) {

            TextView user_name = (TextView) mView.findViewById(R.id.name_text);
            TextView user_skills = (TextView) mView.findViewById(R.id.skills_text);
            CircleImageView user_image = mView.findViewById(R.id.profile_picture);


            user_name.setText(userName);
            user_skills.setText(userskills);
            Picasso.get().load(userImage).into(user_image);


        }
    }
}