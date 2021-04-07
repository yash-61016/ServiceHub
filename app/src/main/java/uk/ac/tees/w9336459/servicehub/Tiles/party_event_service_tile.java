package uk.ac.tees.w9336459.servicehub.Tiles;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import uk.ac.tees.w9336459.servicehub.R;

public class party_event_service_tile extends AppCompatActivity {

    ImageView titleImage;
    static ImageButton wedding_planning, other_services;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_event_service_tile);
        titleImage = findViewById(R.id.TitleImage);

        wedding_planning = findViewById(R.id.wedding_planning);
        other_services = findViewById(R.id.other);


        wedding_planning.setOnClickListener((v)->{

            Intent i = new Intent(party_event_service_tile.this, party_event_service_offered.class);
            i.putExtra("resId", R.drawable.wedding_planner_tile);
            startActivity(i);
            finishActivity(0);
        });
        other_services.setOnClickListener((v)->{
            Intent i = new Intent(party_event_service_tile.this, party_event_service_offered.class);
            i.putExtra("resId", R.drawable.other_events_tile);
            startActivity(i);
            finishActivity(0);
        });
    }
}