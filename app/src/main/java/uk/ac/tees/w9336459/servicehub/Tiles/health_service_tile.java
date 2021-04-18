package uk.ac.tees.w9336459.servicehub.Tiles;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import uk.ac.tees.w9336459.servicehub.R;

public class health_service_tile extends AppCompatActivity {


    ImageView titleImage;
    static ImageButton dietitian, yoga, psychotherapist;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_service_tile);

        titleImage = findViewById(R.id.TitleImage);

        dietitian = findViewById(R.id.diet);
        yoga = findViewById(R.id.yoga);
        psychotherapist = findViewById(R.id.psychotherpy);


        dietitian.setOnClickListener((v)->{
            String title = "Dietitian";
            Intent i = new Intent(health_service_tile.this, health_services_offered.class);
            i.putExtra("resId", R.drawable.haw_dietitian);
            i.putExtra("title",title);
            startActivity(i);
            finishActivity(0);
        });
        yoga.setOnClickListener((v)->{
            String title = "Yoga and Fitness";
            Intent i = new Intent(health_service_tile.this, health_services_offered.class);
            i.putExtra("resId", R.drawable.haw_yogaandfitness);
            i.putExtra("title",title);
            startActivity(i);
            finishActivity(0);
        });
        psychotherapist.setOnClickListener((v)->{
            String title = "Psychotherapist";
            Intent i = new Intent(health_service_tile.this, health_services_offered.class);
            i.putExtra("resId", R.drawable.haw_psychotherapist);
            i.putExtra("title",title);
            startActivity(i);
            finishActivity(0);
        });

    }
}