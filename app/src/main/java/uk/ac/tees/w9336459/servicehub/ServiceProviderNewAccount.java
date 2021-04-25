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

import uk.ac.tees.w9336459.servicehub.Model.ServiceProviders2;

import static uk.ac.tees.w9336459.servicehub.U_MainScreen.decodeUserEmail;

public class ServiceProviderNewAccount extends AppCompatActivity {
    TextInputEditText reg_f_name, reg_l_name,reg_email, reg_password, reg_v_password,reg_yearofexp;
    static TextInputEditText reg_mobile;
    Button loginhome;
    TextView goTo_Login;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    String newNum;

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
        reg_password = findViewById(R.id.SP_CA_Password);
        reg_v_password = findViewById(R.id.SP_CA_VerifyPassword);
        reg_yearofexp = findViewById(R.id.SP_CA_yearsofexp);


        loginhome.setOnClickListener((v)->
        {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("ServiceProviders");

            if (reg_f_name.length() == 0) {
                reg_f_name.requestFocus();
                reg_f_name.setError("FIELD CANNOT BE EMPTY");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_f_name.setBackground(dr);

            } else if (reg_l_name.length() == 0) {
                reg_l_name.requestFocus();
                reg_l_name.setError("FIELD CANNOT BE EMPTY");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_l_name.setBackground(dr);

            } else if (reg_email.length() == 0 || !reg_email.getText().toString().contains("@")) {
                reg_email.requestFocus();
                reg_email.setError("FIELD CANNOT BE EMPTY/ ENTER VALID TEXT");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_email.setBackground(dr);
            } else if (reg_mobile.length() < 10) {
                reg_mobile.requestFocus();
                reg_mobile.setError("FIELD CANNOT BE EMPTY/ENTER VALID MOBILE NUMBER");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_mobile.setBackground(dr);
            }else if (reg_password.length() <= 7 || !(reg_password.getText().toString().matches("(.*[0-9].*)"))
                    || !(reg_password.getText().toString().matches("(.*[A-Z].*)"))) {
                reg_password.requestFocus();
                reg_password.setError("FIELD CANNOT BE EMPTY");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_password.setBackground(dr);
            } else if (reg_v_password.length() <= 7 || !(reg_v_password.getText().toString().matches("(.*[0-9].*)"))
                    || !(reg_v_password.getText().toString().matches("(.*[A-Z].*)")) || !reg_v_password.getText().toString().matches(reg_password.getText().toString())) {
                reg_v_password.requestFocus();
                reg_v_password.setError("FIELD CANNOT BE EMPTY/ 8 characters in length, 1 Uppercase Letter,1 Lowercase Letter, 1 Number, and no symbols");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_v_password.setBackground(dr);
            }  else if (reg_yearofexp.length() == 0 || (reg_yearofexp.getText().toString().matches("(.*[A-Z].*)")) || (reg_yearofexp.getText().toString().matches("(.*[a-z].*)"))) {
                reg_yearofexp.requestFocus();
                reg_yearofexp.setError("FIELD CANNOT BE EMPTY/ Enter a number");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_yearofexp.setBackground(dr);
            } else {
                mAuth.createUserWithEmailAndPassword(reg_email.getText().toString(), reg_password.getText().toString()).addOnCompleteListener(ServiceProviderNewAccount.this, task -> {
                    String ext = "";
                    String ext_check = "";

                    if (task.isSuccessful()) {
                        Toast.makeText(ServiceProviderNewAccount.this, "Validation Successful", Toast.LENGTH_LONG).show();
                        String new_email = "";
                        String fName = reg_f_name.getText().toString();
                        String lName = reg_l_name.getText().toString();
                        String Email = reg_email.getText().toString();
                        if (reg_mobile.getText().toString().startsWith("0")) {
                            newNum = reg_mobile.getText().toString().substring(1);
                        }else if (reg_mobile.getText().toString().startsWith("+44")){
                            newNum = reg_mobile.getText().toString().substring(3);
                        }else{
                            newNum = reg_mobile.getText().toString();
                        }
                        String yearofexp = reg_yearofexp.getText().toString();

                        final Bundle bundle = new Bundle();
                        bundle.putString("firstname",fName);
                        bundle.putString("lastname",lName);
                        bundle.putString("emailid",Email);
                        bundle.putString("phonenum",newNum);
                        bundle.putString("yearsofexp",yearofexp);


                        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                Toast.makeText(ServiceProviderNewAccount.this, "User Registered Successfully, Please verify Email!!!", Toast.LENGTH_LONG).show();
                                Intent a = new Intent(ServiceProviderNewAccount.this, ServiceProviderNewAccountVerify.class);
                                a.putExtra("number", newNum );
                                a.putExtras(bundle);
                                startActivity(a);
                            } else {
                                Toast.makeText(ServiceProviderNewAccount.this, "Error Occured!!Signup Unsuccessful", Toast.LENGTH_LONG).show();
                            }
                        });


                    } else {
                        Toast.makeText(ServiceProviderNewAccount.this, "Error Occured!!Signup Unsuccessful", Toast.LENGTH_LONG).show();
                    }

                });
            }

            goTo_Login.setOnClickListener((view) -> {

                Intent i = new Intent(ServiceProviderNewAccount.this, ServiceProviderLogin2.class);
                startActivity(i);
                finish();
            });

        });
    }

    static String encodeUserEmail (String userEmail){
            return userEmail.replace(".", ",");
        }


    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){
            //already login
        }
    }

}