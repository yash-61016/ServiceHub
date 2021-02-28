package uk.ac.tees.w9336459.servicehub;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class welcome extends AppCompatActivity {

    TextView t1 , t2;
    Button B1, B2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        t1 = findViewById(R.id.welcome);
        t2 = findViewById(R.id.signInAs);

        B1 = findViewById(R.id.User_bt);
        B2 = findViewById(R.id.ServiceProvider_bt);


}
}