package uk.ac.tees.w9336459.servicehub.Tiles;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import uk.ac.tees.w9336459.servicehub.R;

public class personal_grooming_service_tile extends AppCompatActivity {

    ImageView titleImage;
    static ImageButton hairs, massage, manicure, facial_makeup;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_grooming_service_tile);
        titleImage = findViewById(R.id.TitleImage);

        hairs = findViewById(R.id.hair_dressing);
        massage = findViewById(R.id.massage);
        manicure = findViewById(R.id.manicure);
        facial_makeup = findViewById(R.id.facial);

        hairs.setOnClickListener((v)->{

            Intent i = new Intent(personal_grooming_service_tile.this, personal_grooming_offered.class);
            i.putExtra("resId", R.drawable.hair_dressing_tile);
            startActivity(i);
            finishActivity(0);
        });
        massage.setOnClickListener((v)->{
            Intent i = new Intent(personal_grooming_service_tile.this, personal_grooming_offered.class);
            i.putExtra("resId", R.drawable.massages_tile);
            startActivity(i);
            finishActivity(0);
        });
        manicure.setOnClickListener((v)->{
            Intent i = new Intent(personal_grooming_service_tile.this, personal_grooming_offered.class);
            i.putExtra("resId", R.drawable.manicure_pedicure_tile);
            startActivity(i);
            finishActivity(0);
        });
        facial_makeup.setOnClickListener((v)->{
            Intent i = new Intent(personal_grooming_service_tile.this, personal_grooming_offered.class);
            i.putExtra("resId", R.drawable.facial_makeup_tile);
            startActivity(i);
            finishActivity(0);
        });
    }
}