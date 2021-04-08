package uk.ac.tees.w9336459.servicehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServiceProviderNewAccount extends AppCompatActivity {
    TextInputEditText reg_f_name, reg_l_name,reg_email, reg_address1, reg_password, reg_v_password,reg_postcode,reg_account,reg_sortcode;
    static TextInputEditText reg_mobile;
    Button loginhome;
    TextView goTo_Login;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_new_account);

        mAuth = FirebaseAuth.getInstance();

        loginhome = findViewById(R.id.SP_CA_Donebt);
        goTo_Login = findViewById(R.id.Sp_CreateAc);

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
            }else if(reg_password.length()<=7||!(reg_password.getText().toString().matches("(.*[0-9].*)"))
                    ||!(reg_password.getText().toString().matches("(.*[A-Z].*)")))
            {
                reg_password.requestFocus();
                reg_password.setError("FIELD CANNOT BE EMPTY");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_password.setBackground(dr);
            }else if( reg_v_password.length()<=7||!(reg_v_password.getText().toString().matches("(.*[0-9].*)"))
                    ||!(reg_v_password.getText().toString().matches("(.*[A-Z].*)")) )
            {
                reg_v_password.requestFocus();
                reg_v_password.setError("FIELD CANNOT BE EMPTY");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_v_password.setBackground(dr);
            }else if(reg_postcode.length()==0  )
            {
                reg_postcode.requestFocus();
                reg_postcode.setError("FIELD CANNOT BE EMPTY");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_postcode.setBackground(dr);
            }else if(reg_account.length()>0)
            {   if(reg_account.length()!=8){
                reg_account.requestFocus();
                reg_account.setError("PLEASE ENTER CORRECT ACCOUNT NUMBER");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_account.setBackground(dr);
            }
            }else if(reg_sortcode.length()!=6 )
            {   if(reg_sortcode.length()!=8){
                reg_sortcode.requestFocus();
                reg_sortcode.setError("PLEASE ENTER CORRECT SORT CODE");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_sortcode.setBackground(dr);
            }
            }
            else
            {
                mAuth.createUserWithEmailAndPassword(reg_email.getText().toString(),reg_password.getText().toString()).addOnCompleteListener(ServiceProviderNewAccount.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String ext = "";
                        String ext_check="";
                        String newNum = "";

                        if (task.isSuccessful()) {
                            Toast.makeText(ServiceProviderNewAccount.this, "Validation Successful", Toast.LENGTH_LONG).show();
                            String new_email = "";
                            String Name = reg_f_name.getText().toString() + reg_l_name.getText().toString();
                            int index_check = reg_email.getText().toString().lastIndexOf(".");
                            ext_check = reg_email.getText().toString().substring(index_check, index_check+3);
                            if (reg_email.getText().toString().contains(ext_check)) {

                                int index = reg_email.getText().toString().lastIndexOf(".");
                                new_email = reg_email.getText().toString().substring(0, index);
                                ext = reg_email.getText().toString().substring(index, index+3);
                            }
                            String Email = new_email.concat(ext);
                            if(reg_mobile.getText().toString().startsWith("0")) {
                                newNum = reg_mobile.getText().toString().substring(1);
                            }
                            String Number = newNum;
                            String Address = reg_address1.getText().toString();
                            String Postcode = reg_postcode.getText().toString();
                            String AccountNumber = reg_account.getText().toString();
                            String SortCode = reg_sortcode.getText().toString();
                            ServiceProviderHelperClass helperClass = new ServiceProviderHelperClass(Name,Number,Address,Email,AccountNumber,SortCode,Postcode);
                            reference.child("Details").child(Number).setValue(helperClass);
                            Intent a = new Intent(ServiceProviderNewAccount.this, ServiceProviderNewAccountVerify.class);
                            startActivity(a);
                        }else{
                            Toast.makeText(ServiceProviderNewAccount.this,"Error Occured!!Signup Unsuccessful",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        goTo_Login.setOnClickListener((v)->{

            Intent i = new Intent(ServiceProviderNewAccount.this, ServiceProviderLogin2.class);
            startActivity(i);
            finish();
        });

    }
}
