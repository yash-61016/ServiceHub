package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class User_Create_Account extends AppCompatActivity {

    EditText f_name, l_name, email,mobile, address1, address2, address3, password, v_password;
    Button loginhome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__create__account);

        loginhome = findViewById(R.id.U_CA_LoginInbt);

        f_name = findViewById(R.id.U_CA_First_name);
        l_name = findViewById(R.id.U_CA_Last_name);
        email = findViewById(R.id.U_CA_Email);
        mobile = findViewById(R.id.U_CA_number);
        address1 = findViewById(R.id.U_CA_Ad1);
        address2 = findViewById(R.id.U_CA_Ad2);
        address3 = findViewById(R.id.U_CA_Ad3);
        password = findViewById(R.id.U_CA_P_enter);
        v_password = findViewById(R.id.U_CA_VP_enter);

        loginhome.setOnClickListener((v)->
        {

            Intent a = new Intent(User_Create_Account.this, User_VerifyID_Screen.class);
            startActivity(a);
        });

    }
}