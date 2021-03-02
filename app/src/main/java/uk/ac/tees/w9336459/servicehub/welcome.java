package uk.ac.tees.w9336459.servicehub;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class welcome extends AppCompatActivity {

    Button btUser,sp;
    TextView t1 , t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        sp = findViewById(R.id.ServiceProvider);

        sp.setOnClickListener((v)->{

            Intent a = new Intent(welcome.this , ServiceProviderLogin.class);
            startActivity(a);

        });


        btUser = findViewById(R.id.User);

        btUser.setOnClickListener((v)->{
            Intent a = new Intent(welcome.this , UserSignUp.class);
            startActivity(a);

        });
        t1 = findViewById(R.id.welcome);
        t2 = findViewById(R.id.signInAs);

}
}