package uk.ac.tees.w9336459.servicehub.Tiles;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import uk.ac.tees.w9336459.servicehub.R;

import static android.graphics.drawable.Drawable.createFromPath;

public class electronic_tile extends AppCompatActivity {

    ImageView titleImage;
    static ImageButton laptop_bt, tv_btn, radio_btn, mobiles_btn;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic_tile);

        titleImage = findViewById(R.id.TitleImage);

        laptop_bt = findViewById(R.id.ET_bt1);
        tv_btn = findViewById(R.id.ET_bt2);
        radio_btn = findViewById(R.id.ET_bt3);
        mobiles_btn = findViewById(R.id.ET_bt4);

        laptop_bt.setOnClickListener((v)->{

            Intent i = new Intent(electronic_tile.this, electronic_service_offered.class);
            i.putExtra("resId", R.drawable.laptop_tile);
            startActivity(i);
            finishActivity(0);
        });
        tv_btn.setOnClickListener((v)->{
            Intent i = new Intent(electronic_tile.this, electronic_service_offered.class);
            i.putExtra("resId", R.drawable.telivision_tile);
            startActivity(i);
            finishActivity(0);
        });
        radio_btn.setOnClickListener((v)->{
            Intent i = new Intent(electronic_tile.this, electronic_service_offered.class);
            i.putExtra("resId", R.drawable.music_system_tile);
            startActivity(i);
            finishActivity(0);
        });
        mobiles_btn.setOnClickListener((v)->{
            Intent i = new Intent(electronic_tile.this, electronic_service_offered.class);
            i.putExtra("resId", R.drawable.mobile_tile);
            startActivity(i);
            finishActivity(0);
        });
    }
}