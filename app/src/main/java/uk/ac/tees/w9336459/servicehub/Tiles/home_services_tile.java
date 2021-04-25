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

public class home_services_tile extends AppCompatActivity {


    ImageView titleImage;
    static ImageButton interior_designing, plumbing, painting;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_services_tile);

        titleImage = findViewById(R.id.TitleImage);

        interior_designing = findViewById(R.id.interior);
        plumbing = findViewById(R.id.plumbing);
        painting = findViewById(R.id.painting);

        interior_designing.setOnClickListener((v)->{
            String title = "Interior Designing";
            Intent i = new Intent(home_services_tile.this, home_service_offered.class);
            i.putExtra("resId", R.drawable.hm_interiorsevices);
            i.putExtra("title",title);
            startActivity(i);
            finishActivity(0);
        });
        plumbing.setOnClickListener((v)->{
            String title = "Plumbing";
            Intent i = new Intent(home_services_tile.this, home_service_offered.class);
            i.putExtra("resId", R.drawable.hm_plumbimg);
            i.putExtra("title",title);
            startActivity(i);
            finishActivity(0);
        });
        painting.setOnClickListener((v)->{
            String title = "Painting";
            Intent i = new Intent(home_services_tile.this, home_service_offered.class);
            i.putExtra("resId", R.drawable.hm_painting);
            i.putExtra("title",title);
            startActivity(i);
            finishActivity(0);
        });
    }
    public void onServiceClick(View view) {

        Intent intent = new Intent(home_services_tile.this,electronic_service_offered.class);
        startActivity(intent);
    }
}