package uk.ac.tees.w9336459.servicehub.Tiles;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import uk.ac.tees.w9336459.servicehub.R;

public class home_services_tile extends AppCompatActivity {


    ImageView titleImage;
    static ImageButton interior_service, plumbing, painting, other_service;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_services_tile);

        titleImage = findViewById(R.id.TitleImage);

        interior_service = findViewById(R.id.interior);
        plumbing = findViewById(R.id.plumbing);
        painting = findViewById(R.id.painting);
        other_service = findViewById(R.id.other);

        interior_service.setOnClickListener((v)->{

            Intent i = new Intent(home_services_tile.this, home_service_offered.class);
            i.putExtra("resId", R.drawable.interior_desgine_tile);
            startActivity(i);
            finishActivity(0);
        });
        plumbing.setOnClickListener((v)->{
            Intent i = new Intent(home_services_tile.this, home_service_offered.class);
            i.putExtra("resId", R.drawable.plumbing_tile);
            startActivity(i);
            finishActivity(0);
        });
        painting.setOnClickListener((v)->{
            Intent i = new Intent(home_services_tile.this, home_service_offered.class);
            i.putExtra("resId", R.drawable.painting_tile);
            startActivity(i);
            finishActivity(0);
        });
        other_service.setOnClickListener((v)->{
            Intent i = new Intent(home_services_tile.this, home_service_offered.class);
            i.putExtra("resId", R.drawable.other_housekeeping_tile);
            startActivity(i);
            finishActivity(0);
        });
    }
}