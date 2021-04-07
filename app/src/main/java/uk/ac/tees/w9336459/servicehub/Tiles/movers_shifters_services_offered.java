package uk.ac.tees.w9336459.servicehub.Tiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import uk.ac.tees.w9336459.servicehub.R;

public class movers_shifters_services_offered extends AppCompatActivity {

    static ImageView titleimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movers_shifters_services_offered);

        titleimage= findViewById(R.id.TitleImage);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("resId");
            titleimage.setBackgroundResource(resId);
        }

    }
}