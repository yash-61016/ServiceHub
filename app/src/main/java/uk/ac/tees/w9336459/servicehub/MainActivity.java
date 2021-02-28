package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button getStarted;
    TextView t1 , t2 , t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            getStarted = findViewById(R.id.getstarted);
            t1 = findViewById(R.id.serviceHub);
            t2 = findViewById(R.id.getAnything);
            t3 = findViewById(R.id.sitting);

            getStarted.setOnClickListener((view)->{
                Intent a = new Intent(MainActivity.this , welcome.class);
                startActivity(a);
            });
        }
    }
