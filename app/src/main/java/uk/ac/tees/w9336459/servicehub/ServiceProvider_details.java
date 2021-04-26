package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ServiceProvider_details extends AppCompatActivity {
    CircleImageView profileimageview;
    TextView email,phone_num,address;
    TextView Username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_details);

        profileimageview=findViewById(R.id.User_profilepic);

        // reading the data from the current user

        email = (TextView)findViewById(R.id.User_emailchange);
        phone_num = (TextView)findViewById(R.id.User_numberchange);
        address = (TextView)findViewById(R.id.User_addresschange);
        Username = (TextView)findViewById(R.id.User_name);

        readuser();
    }

    public void readuser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String EmailChildID = U_MainScreen.decodeUserEmail(user.getEmail());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ServiceProviders").child("Details").child(EmailChildID);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Read from the database
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    String Email = dataSnapshot.child("emailid").getValue(String.class);
                    email.setText(Email);
                    String PhoneNum = dataSnapshot.child("phonenum").getValue(String.class);
                    phone_num.setText(PhoneNum);
                    String Address = dataSnapshot.child("address").getValue(String.class);
                    address.setText(Address);

                    String fname = dataSnapshot.child("firstname").getValue(String.class);
                    String lname = dataSnapshot.child("lastname").getValue(String.class);
                    Username.setText(fname+" "+lname);


                    if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                        if(dataSnapshot.child("profilepicture").getValue().toString().equals("null")){
                            Picasso.get().load(R.drawable.avatar).into(profileimageview);
                        }
                        else  {
                            String image = dataSnapshot.child("profilepicture").getValue().toString();
                            Picasso.get().load(image).into(profileimageview);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}