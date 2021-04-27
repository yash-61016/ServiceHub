package uk.ac.tees.w9336459.servicehub.Tiles;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import uk.ac.tees.w9336459.servicehub.R;

public class personal_grooming_service_tile extends AppCompatActivity {

    ImageView titleImage;
    static ImageButton hairsdresser, massage, manicure, facial_makeup;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_grooming_service_tile);
        titleImage = findViewById(R.id.TitleImage);

        hairsdresser = findViewById(R.id.hair_dresser);
        massage = findViewById(R.id.massage);
        manicure = findViewById(R.id.manicure);
        facial_makeup = findViewById(R.id.facial);

        hairsdresser.setOnClickListener((v)->{
            String title = "Hairdresser";
            Intent i = new Intent(personal_grooming_service_tile.this, personal_grooming_offered.class);
            i.putExtra("resId", R.drawable.pg_hairdressing);
            i.putExtra("title",title);
            startActivity(i);
            finishActivity(0);
        });
        massage.setOnClickListener((v)->{
            String title = "Masseuse";
            Intent i = new Intent(personal_grooming_service_tile.this, personal_grooming_offered.class);
            i.putExtra("resId", R.drawable.pg_massage);
            i.putExtra("title",title);
            startActivity(i);
            finishActivity(0);
        });
        manicure.setOnClickListener((v)->{
            String title = "Manicure and Pedicure";
            Intent i = new Intent(personal_grooming_service_tile.this, personal_grooming_offered.class);
            i.putExtra("resId", R.drawable.pg_manicureandpedicure);
            i.putExtra("title",title);
            startActivity(i);
            finishActivity(0);
        });
        facial_makeup.setOnClickListener((v)->{
            String title = "Facial and Makeup";
            Intent i = new Intent(personal_grooming_service_tile.this, personal_grooming_offered.class);
            i.putExtra("resId", R.drawable.pg_facial);
            i.putExtra("title",title);
            startActivity(i);
            finishActivity(0);
        });
    }
    public void onServiceClick(View view) {

        Intent intent = new Intent(personal_grooming_service_tile.this,electronic_service_offered.class);
        startActivity(intent);
    }
}