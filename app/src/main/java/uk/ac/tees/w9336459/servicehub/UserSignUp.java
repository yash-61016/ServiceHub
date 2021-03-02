package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserSignUp extends AppCompatActivity {

    Button login,create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        login = findViewById(R.id.U_Sp_Login);
        create = findViewById(R.id.U_Sp_Createbt);

        login.setOnClickListener(view -> {
                Intent a = new Intent(UserSignUp.this, User_Login.class);
                startActivity(a);
        });
        create.setOnClickListener(view -> {
                Intent a = new Intent(UserSignUp.this, User_Create_Account.class);
                startActivity(a);
        });

    }
}