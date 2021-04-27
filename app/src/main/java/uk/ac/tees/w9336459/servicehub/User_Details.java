package uk.ac.tees.w9336459.servicehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static uk.ac.tees.w9336459.servicehub.U_MainScreen.decodeUserEmail;

public class User_Details extends AppCompatActivity {
     CircleImageView profileimageview;
     EditText email,phone_num,profile_pic,address;
     TextView Username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__details);

        profileimageview=findViewById(R.id.User_profilepic);

        // reading the data from the current user

        email = findViewById(R.id.User_emailchange);
        phone_num = findViewById(R.id.User_numberchange);
        address = findViewById(R.id.User_addresschange);
        Username = findViewById(R.id.User_name);

        readuser();

    }


    public void readuser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String EmailChildID = U_MainScreen.decodeUserEmail(user.getEmail());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child("Details").child(EmailChildID);
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
                        if(dataSnapshot.child("image").getValue().toString().equals("null")){
                            Picasso.get().load(R.drawable.avatar).into(profileimageview);
                        }
                        else  {
                            String image = dataSnapshot.child("image").getValue().toString();
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