package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServiceProviderNewAccount extends AppCompatActivity {
    TextInputEditText reg_f_name, reg_l_name,reg_mobile,reg_email, reg_address1, reg_password, reg_v_password,reg_postcode,reg_account,reg_sortcode;
    Button loginhome;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_new_account);

        loginhome = findViewById(R.id.SP_CA_LoginInbt);

        reg_f_name = findViewById(R.id.SP_CA_FirstName);
        reg_l_name = findViewById(R.id.SP_CA_LastName);
        reg_email =  findViewById(R.id.SP_CA_Email);
        reg_mobile = findViewById(R.id.SP_CA_number);
        reg_address1 =findViewById(R.id.SP_CA_Address);
        reg_postcode = findViewById(R.id.SP_CA_Postcode);
        reg_password = findViewById(R.id.SP_CA_Password);
        reg_v_password = findViewById(R.id.SP_CA_VerifyPassword);
        reg_account = findViewById(R.id.SP_CA_AccountNO);
        reg_sortcode = findViewById(R.id.SP_CA_SortCode);


        loginhome.setOnClickListener((v)->
        {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("ServiceProviders");
            String new_email = "";
            String name =  reg_f_name.getText().toString() + reg_l_name.getText().toString();
            if(reg_email.getText().toString().contains(".com")){

                int index = reg_email.getText().toString().indexOf(".");
                new_email = reg_email.getText().toString().substring(0,index);
            }
            String email = new_email.concat(".com");
            String number = reg_mobile.getText().toString();
            String address = reg_address1.getText().toString();
            String Postcode = reg_postcode.getText().toString();
            String password = reg_password.getText().toString();
            String verify_password = reg_v_password.getText().toString();
            String account = reg_account.getText().toString();
            String sortcode = reg_sortcode.getText().toString();
            if(reg_f_name.length()==0 )
            {
                reg_f_name.requestFocus();
                reg_f_name.setError("FIELD CANNOT BE EMPTY");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_f_name.setBackground(dr);

            }else if(reg_email.length()==0 || !reg_email.getText().toString().contains("@"))
            {
                reg_email.requestFocus();
                reg_email.setError("FIELD CANNOT BE EMPTY/ ENTER VALID TEXT");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_email.setBackground(dr);
            }else if(reg_mobile.length()!=10)
            {
                reg_mobile.requestFocus();
                reg_mobile.setError("FIELD CANNOT BE EMPTY/ENTER VALID MOBILE NUMBER");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_mobile.setBackground(dr);
            }else if(reg_address1.length()==0 )
            {
                reg_address1.requestFocus();
                reg_address1.setError("FIELD CANNOT BE EMPTY");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_address1.setBackground(dr);
            }else if(reg_password.length()==0 )
            {
                reg_password.requestFocus();
                reg_password.setError("FIELD CANNOT BE EMPTY");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_password.setBackground(dr);
            }else if(reg_v_password.length()==0 )
            {
                reg_v_password.requestFocus();
                reg_v_password.setError("FIELD CANNOT BE EMPTY");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_v_password.setBackground(dr);
            }else if(reg_postcode.length()==0 )
            {
                reg_postcode.requestFocus();
                reg_postcode.setError("FIELD CANNOT BE EMPTY");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_postcode.setBackground(dr);
            }else if(reg_account.length()==0 )
            {
                reg_account.requestFocus();
                reg_account.setError("FIELD CANNOT BE EMPTY");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_account.setBackground(dr);
            }else if(reg_sortcode.length()==0 )
            {
                reg_sortcode.requestFocus();
                reg_sortcode.setError("FIELD CANNOT BE EMPTY");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_sortcode.setBackground(dr);
            }
            else
            {
                Toast.makeText(ServiceProviderNewAccount.this,"Validation Successful",Toast.LENGTH_LONG).show();
                ServiceProviderHelperClass helperClass = new ServiceProviderHelperClass(name, email, number, address,Postcode, password, verify_password,account,sortcode);
                reference.child(number).setValue(helperClass);
                Intent a = new Intent(ServiceProviderNewAccount.this, ServiceProviderNewAccountVerify.class);
                startActivity(a);
            }
        });

    }
}
