package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServiceProviderNewAccount extends AppCompatActivity {
    EditText regFirstName,reglastName, regEmail,regNumber,regPassword,regAddress1,regAddress2,regAddress3,regAccountNo,regSortCode;
    Button next;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_new_account);

        next = findViewById(R.id.SP_NA__Next);
        regFirstName = findViewById(R.id.SP_NA_First_nameTXT);
        reglastName = findViewById(R.id.SP_NA_Last_nameTXT);
        regEmail = findViewById(R.id.SP_NA_EmailTXT);
        regNumber = findViewById(R.id.SP_NA_MobileNOTXT);
        regAddress1 = findViewById(R.id.SP_NA_Address1TXT);
        regAddress2 = findViewById(R.id.SP_NA_Address2TXT);
        regAddress3 = findViewById(R.id.SP_NA_Address3TXT);
        regAccountNo = findViewById(R.id.SP_NA_AccountNoTXT);
        regPassword = findViewById(R.id.SP_NA_PasswordTXT);
        regSortCode = findViewById(R.id.SP_NA_SortCodeTXT);

        next.setOnClickListener((v)->{
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("ServiceProviders");
            String name = regFirstName.getText().toString() + reglastName.getText().toString();
            String number = regNumber.getText().toString();
            String email = regEmail.getText().toString();
            String password = regPassword.getText().toString();
            String address = regAddress1.getText().toString() + regAddress2.getText().toString() +regAddress3.getText().toString();
            String accountNum = regAccountNo.getText().toString();
            String sortCode = regSortCode.getText().toString();
            ServiceProviderHelperClass helperClass = new ServiceProviderHelperClass(name,number,address,email, accountNum, sortCode, password);
            reference.child(regEmail.getText().toString()).setValue(helperClass);
            Intent a = new Intent(ServiceProviderNewAccount.this , ServiceProviderNewAccountVerify.class);
            startActivity(a);

        });
    }
}