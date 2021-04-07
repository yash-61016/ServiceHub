package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class menubtn extends AppCompatActivity {

    ImageView image;
    Button app_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menubtn);
        app_help = findViewById(R.id.U_M_help);

        app_help.setOnClickListener((v)->{

            Intent a = new Intent(menubtn.this, help.class);
            startActivity(a);
            finish();

        });
    }
}