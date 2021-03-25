package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class U_MainScreen extends AppCompatActivity {

    ScrollView sc;
    Button Er_bt , hs_bt, hos_bt, mbt, p_and_ebt,ps_bt , ms_bt;
    ImageView profile_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u__main_screen);

        sc = findViewById(R.id.Scroll);
        sc.setVerticalScrollBarEnabled(true);
        Er_bt = findViewById(R.id.U_Ms_ElectronicRepairs);
        hs_bt = findViewById(R.id.U_Ms_ElectronicRepairs);

        ms_bt = findViewById(R.id.U_Ms_Menubt);
        profile_btn = findViewById(R.id.profile);


        ms_bt.setOnClickListener((v)->{
            Intent a = new Intent(U_MainScreen.this,menubtn.class);
            startActivity(a);
        });

        profile_btn.setOnClickListener((V)->{
            Intent i = new Intent(U_MainScreen.this, profile.class);
            startActivity(i);
        });
    }
}