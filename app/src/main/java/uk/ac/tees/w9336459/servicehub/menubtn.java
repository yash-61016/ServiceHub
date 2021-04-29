package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class menubtn extends AppCompatActivity {

    CircleImageView profilepic;
    Button app_help , contact_us,changelocationUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menubtn);
        app_help = findViewById(R.id.U_M_help);
        contact_us = findViewById(R.id.U_M_contactUs);
        profilepic = findViewById(R.id.profile_pic);
        changelocationUser= findViewById(R.id.changelocation);
        String image = getIntent().getStringExtra("profilepic");
        Picasso.get().load(image).into(profilepic);

        app_help.setOnClickListener((v)->{

            Intent a = new Intent(menubtn.this, help.class);
            startActivity(a);
            finish();

        });

        contact_us.setOnClickListener((v)->{
            Intent contact = new Intent(menubtn.this,contact_us.class);
            startActivity(contact);

        });

        profilepic.setOnClickListener((v)->{
            Intent i = new Intent(this, profile.class);
            startActivity(i);
        });

        changelocationUser.setOnClickListener((v)->{
            Intent intent = new Intent(menubtn.this,ChangeLocationUser.class);
            startActivity(intent);
        });



    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, U_MainScreen.class));
    }


}