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
    static ImageButton diet, yoga, psychotherpy ;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_service_tile);

        titleImage = findViewById(R.id.TitleImage);

        diet = findViewById(R.id.diet);
        yoga = findViewById(R.id.yoga);
        psychotherpy = findViewById(R.id.psychotherpy);


        diet.setOnClickListener((v)->{

            Intent i = new Intent(health_service_tile.this, health_services_offered.class);
            i.putExtra("resId", R.drawable.dietian_tile);
            startActivity(i);
            finishActivity(0);
        });
        yoga.setOnClickListener((v)->{
            Intent i = new Intent(health_service_tile.this, health_services_offered.class);
            i.putExtra("resId", R.drawable.yoga_fitness_tile);
            startActivity(i);
            finishActivity(0);
        });
        psychotherpy.setOnClickListener((v)->{
            Intent i = new Intent(health_service_tile.this, health_services_offered.class);
            i.putExtra("resId", R.drawable.psychotherapist_tile);
            startActivity(i);
            finishActivity(0);
        });

    }
}