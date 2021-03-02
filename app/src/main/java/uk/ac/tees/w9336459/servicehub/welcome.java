package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class welcome extends AppCompatActivity {

    Button btUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btUser = findViewById(R.id.User);

        btUser.setOnClickListener((v)->{
            Intent a = new Intent(welcome.this , UserSignUp.class);
            startActivity(a);

        });

}
}