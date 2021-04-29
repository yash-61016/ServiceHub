package uk.ac.tees.w9336459.servicehub.Tiles;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import uk.ac.tees.w9336459.servicehub.Customers;
import uk.ac.tees.w9336459.servicehub.R;
import uk.ac.tees.w9336459.servicehub.U_MainScreen;
import uk.ac.tees.w9336459.servicehub.menubtn;

public class electronic_tile extends AppCompatActivity {

    ImageView titleImage , menubtn;
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
        menubtn = findViewById(R.id.menu);

        menubtn.setOnClickListener((v)->{
                Intent a = new Intent(electronic_tile.this, uk.ac.tees.w9336459.servicehub.menubtn.class);
                startActivity(a);
                finish();
        });

        laptop_bt.setOnClickListener((v) -> {

            String title = "Laptop and Desktop Repairing";
            Intent i = new Intent(electronic_tile.this, electronic_service_offered.class);
            i.putExtra("resId1", R.drawable.er_laptop);
            i.putExtra("title", title);
            startActivity(i);
            finishActivity(0);
        });
        tv_btn.setOnClickListener((v) -> {
            Intent i = new Intent(electronic_tile.this, electronic_service_offered.class);
            i.putExtra("resId1", R.drawable.er_tv);
            i.putExtra("title", "Television Repairing");
            startActivity(i);
            finishActivity(0);
        });
        radio_btn.setOnClickListener((v) -> {
            Intent i = new Intent(electronic_tile.this, electronic_service_offered.class);
            i.putExtra("resId1", R.drawable.er_radio);
            i.putExtra("title", "Music System Repairing");
            startActivity(i);
            finishActivity(0);
        });
        mobiles_btn.setOnClickListener((v) -> {
            Intent i = new Intent(electronic_tile.this, electronic_service_offered.class);
            i.putExtra("resId1", R.drawable.er_smartphone);
            i.putExtra("title", "Smartphone Repairing");
            startActivity(i);
            finishActivity(0);
        });
    }

    public void onServiceClick(View view) {

        Intent intent = new Intent(electronic_tile.this,electronic_service_offered.class);
        startActivity(intent);
    }

}