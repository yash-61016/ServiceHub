package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome extends AppCompatActivity {

    Button sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        sp = findViewById(R.id.ServiceProvider);

        sp.setOnClickListener((v)->{

            Intent a = new Intent(welcome.this , ServiceProviderLogin.class);
            startActivity(a);

        });


}
}