package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class User_Create_Account extends AppCompatActivity {

    EditText reg_f_name, reg_l_name, reg_email,reg_mobile, reg_address1, reg_address2, reg_address3, reg_password, reg_v_password;
    Button loginhome;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__create__account);

        loginhome = findViewById(R.id.U_CA_LoginInbt);

        reg_f_name = findViewById(R.id.U_CA_First_name);
        reg_l_name = findViewById(R.id.U_CA_Last_name);
        reg_email = findViewById(R.id.U_CA_Email);
        reg_mobile = findViewById(R.id.U_CA_number);
        reg_address1 = findViewById(R.id.U_CA_Ad1);
        reg_address2 = findViewById(R.id.U_CA_Ad2);
        reg_address3 = findViewById(R.id.U_CA_Ad3);
        reg_password = findViewById(R.id.U_CA_P_enter);
       reg_v_password = findViewById(R.id.U_CA_VP_enter);

        loginhome.setOnClickListener((v)->
        {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Users");
            String name = reg_f_name.getText().toString() +" "+reg_l_name.getText().toString();
            String number = reg_mobile.getText().toString();
            String email = reg_email.getText().toString();
            String password = reg_password.getText().toString();
            String verify_password = reg_v_password.getText().toString();
            String address = reg_address1.getText().toString() +" "+reg_address2.getText().toString() +" "+reg_address3.getText().toString();
            UserAccountHelper helperClass = new UserAccountHelper(name, number, address, email, password , verify_password);
            reference.child(reg_email.getText().toString()).setValue(helperClass);
            Intent a = new Intent(User_Create_Account.this, User_VerifyID_Screen.class);
            startActivity(a);
        });

    }
}