package uk.ac.tees.w9336459.servicehub;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class welcome extends AppCompatActivity {

    Button btUser,sp;
    TextView t1 , t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        t1 = findViewById(R.id.welcome);
        t2 = findViewById(R.id.signInAs);
        sp = findViewById(R.id.ServiceProvider_bt); // sign up as Service provider
        btUser = findViewById(R.id.User_bt); // sign up as user

        sp.setOnClickListener((v)->{

            Intent a = new Intent(welcome.this , ServiceProviderLogin.class);
            startActivity(a);

        });

        btUser.setOnClickListener((v)->{
            Intent a = new Intent(welcome.this , UserSignUp.class);
            startActivity(a);

        });


}
    @Override
    public void onBackPressed() {

            final AlertDialog.Builder builder = new AlertDialog.Builder(welcome.this);
            builder.setMessage("Do you want to exit ?");
            builder.setCancelable(true);
            builder.setNegativeButton("Yes", (dialogInterface, i) -> moveTaskToBack(true));
            builder.setPositiveButton("No", (dialogInterface, i) -> dialogInterface.cancel());
            builder.setInverseBackgroundForced(true);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

    }
}