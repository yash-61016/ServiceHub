package uk.ac.tees.w9336459.servicehub.Tiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import uk.ac.tees.w9336459.servicehub.R;

public class personal_grooming_offered extends AppCompatActivity {

    static ImageView titleimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_grooming_offered);


        titleimage= findViewById(R.id.TitleImage);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("resId");
            titleimage.setBackgroundResource(resId);
        }
    }
}