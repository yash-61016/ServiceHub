package uk.ac.tees.w9336459.servicehub.Tiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import uk.ac.tees.w9336459.servicehub.R;

import static android.graphics.drawable.Drawable.createFromPath;

public class electronic_service_offered extends AppCompatActivity {

    static ImageView titleimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic_service_offered);

        titleimage= findViewById(R.id.TitleImage);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("resId");
            titleimage.setBackgroundResource(resId);
        }
    }

}