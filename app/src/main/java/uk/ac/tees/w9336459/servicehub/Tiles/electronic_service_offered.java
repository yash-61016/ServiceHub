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

public class electronic_service_offered extends AppCompatActivity {

    ImageView titleimage;
    TextView title;
    private DatabaseReference mref;
    private RecyclerView mResultList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic_service_offered);

        titleimage = findViewById(R.id.TitleImage);
        title = findViewById(R.id.U_ES_title);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            int resId = bundle.getInt("resId1");
            titleimage.setBackgroundResource(resId);
            String titleId = getIntent().getStringExtra("title");
            title.setText(titleId);
            if(title.getText().toString().equals("Laptop and Desktop")) {

                mResultList = (RecyclerView) findViewById(R.id.U_ES_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
                mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Profile");
                Toast.makeText(this, "Select the providers1", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Laptop and Desktop");
                mResultList.setVisibility(View.VISIBLE);
            }else if(title.getText().toString().equals("Telivision Repair")) {

                mResultList = (RecyclerView) findViewById(R.id.U_ES_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
                mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Profile");
                Toast.makeText(this, "Select the providers2", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Telivision Repair");
                mResultList.setVisibility(View.VISIBLE);
            }else if(title.getText().toString().equals("Music System Repair")) {

                mResultList = (RecyclerView) findViewById(R.id.U_ES_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
                mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Profile");
                Toast.makeText(this, "Select the providers3", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Music System Repair");
                mResultList.setVisibility(View.VISIBLE);
            }else if(title.getText().toString().equals("Smartphone Repair")) {

                mResultList = (RecyclerView) findViewById(R.id.U_ES_recyclerView);
                mResultList.setHasFixedSize(true);
                mResultList.setLayoutManager(new LinearLayoutManager(this));
                mref = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Profile");
                Toast.makeText(this, "Select the providers4", Toast.LENGTH_LONG).show();
                ChangeRecyclerView("Smartphone Repair");
                mResultList.setVisibility(View.VISIBLE);
            }
        }


    }


    public void ChangeRecyclerView(String service){

        Query fbq = mref.orderByChild("skills").startAt(service).endAt(service+'\uf8ff');

        FirebaseRecyclerAdapter<ServiceProviders, ServiceProviderViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ServiceProviders, ServiceProviderViewHolder>(

                ServiceProviders.class,
                R.layout.list_layout,
                ServiceProviderViewHolder.class,
                fbq

        ) {
            @Override
            protected void populateViewHolder(ServiceProviderViewHolder usersViewHolder, ServiceProviders users, int i) {

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

        public void setDetails( String userName, String userskills, String userImage) {

                TextView user_name = (TextView) mView.findViewById(R.id.name_text);
                TextView user_skills = (TextView) mView.findViewById(R.id.skills_text);
                CircleImageView user_image = mView.findViewById(R.id.profile_picture);


                user_name.setText(userName);
                user_skills.setText(userskills);
                Picasso.get().load(userImage).into(user_image);



        }
    }
}