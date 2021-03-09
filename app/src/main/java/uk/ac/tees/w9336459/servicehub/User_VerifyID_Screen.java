package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class User_VerifyID_Screen extends AppCompatActivity {
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__verify_i_d__screen);

        done = findViewById(R.id.U_NAV__Done);
        done.setOnClickListener((V)->{

            Intent a = new Intent(User_VerifyID_Screen.this , U_MainScreen.class);
            startActivity(a);
        });

    }
}