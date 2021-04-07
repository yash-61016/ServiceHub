package uk.ac.tees.w9336459.servicehub.Tiles;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import uk.ac.tees.w9336459.servicehub.R;

public class mover_shifters_service_tile extends AppCompatActivity {

    ImageView titleImage;
    static ImageButton home_shifting, home_delivery;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mover_shifters_service_tile);

        titleImage = findViewById(R.id.TitleImage);

        home_shifting = findViewById(R.id.home_shifting);
        home_delivery = findViewById(R.id.home_delivery);


        home_shifting.setOnClickListener((v)->{

            Intent i = new Intent(mover_shifters_service_tile.this, movers_shifters_services_offered.class);
            i.putExtra("resId", R.drawable.house_shifting_tile);
            startActivity(i);
            finishActivity(0);
        });
        home_delivery.setOnClickListener((v)->{
            Intent i = new Intent(mover_shifters_service_tile.this, movers_shifters_services_offered.class);
            i.putExtra("resId", R.drawable.home_delivery_tile);
            startActivity(i);
            finishActivity(0);
        });
    }
}