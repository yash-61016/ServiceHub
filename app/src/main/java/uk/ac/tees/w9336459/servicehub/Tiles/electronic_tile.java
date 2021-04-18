package uk.ac.tees.w9336459.servicehub.Tiles;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import uk.ac.tees.w9336459.servicehub.R;

public class electronic_tile extends AppCompatActivity {

    ImageView titleImage;
    static ImageButton laptop_bt, tv_btn, radio_btn, mobiles_btn;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic_tile);

        titleImage = findViewById(R.id.TitleImage);

        laptop_bt = findViewById(R.id.ET_laptop);
        tv_btn = findViewById(R.id.ET_tv);
        radio_btn = findViewById(R.id.ET_musicsystem);
        mobiles_btn = findViewById(R.id.ET_smartphone);

        laptop_bt.setOnClickListener((v)->{

            String title = "Laptop and Desktop";
            Intent i = new Intent(electronic_tile.this, electronic_service_offered.class);
            i.putExtra("resId1", R.drawable.er_laptop);
            i.putExtra("title",title);
            startActivity(i);
            finishActivity(0);
        });
        tv_btn.setOnClickListener((v)->{
            Intent i = new Intent(electronic_tile.this, electronic_service_offered.class);
            i.putExtra("resId1", R.drawable.er_tv);
            i.putExtra("title","Telivision Repair");
            startActivity(i);
            finishActivity(0);
        });
        radio_btn.setOnClickListener((v)->{
            Intent i = new Intent(electronic_tile.this, electronic_service_offered.class);
            i.putExtra("resId1", R.drawable.er_radio);
            i.putExtra("title","Music System Repair");
            startActivity(i);
            finishActivity(0);
        });
        mobiles_btn.setOnClickListener((v)->{
            Intent i = new Intent(electronic_tile.this, electronic_service_offered.class);
            i.putExtra("resId1", R.drawable.er_smartphone);
            i.putExtra("title","Smartphone Repair");
            startActivity(i);
            finishActivity(0);
        });

 }


}