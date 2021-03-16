package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaCodec;
import android.os.Bundle;

import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.graphics.drawable.Drawable.createFromPath;
import static android.text.TextUtils.isEmpty;
import static android.text.TextUtils.replace;


public class User_Create_Account extends AppCompatActivity {


    TextInputEditText reg_f_name;
    TextInputEditText reg_l_name;
    static TextInputEditText reg_mobile;
    TextInputEditText reg_email;
    TextInputEditText reg_address1;
    TextInputEditText reg_password;
    TextInputEditText reg_v_password;
    TextInputEditText reg_postcode;
    Button loginhome,showHideBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__create__account);

        loginhome = findViewById(R.id.U_CA_LoginInbt);
        showHideBtn = findViewById(R.id.Hide);

        reg_f_name = findViewById(R.id.U_CA_FirstName);
        reg_l_name = findViewById(R.id.U_CA_LastName);
        reg_email =  findViewById(R.id.U_CA_Email);
        reg_mobile = findViewById(R.id.U_CA_number);
        reg_address1 =findViewById(R.id.U_CA_Address);
        reg_postcode = findViewById(R.id.U_CA_postcode);
        reg_password = findViewById(R.id.U_CA_Password);
        reg_v_password = findViewById(R.id.U_CA_VerifyPassword);



        loginhome.setOnClickListener((v)->
        {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Users");
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
                    ||!(reg_password.getText().toString().matches("(.*[A-Z].*)"))||!(password.matches("^(?=.*[_.()$&@]).*$")))
            {
                reg_password.requestFocus();
                reg_password.setError("FIELD CANNOT BE EMPTY/ 8 characters in length, 1 Uppercase Letter,1 Lowercase Letter, 1 Number, and no symbols");
                Resources res = getResources();
                Drawable dr = res.getDrawable(R.drawable.border);
                reg_password.setBackground(dr);
             }else if(reg_v_password.length()<=7||!(reg_v_password.getText().toString().matches("(.*[0-9].*)"))
                    ||!(reg_v_password.getText().toString().matches("(.*[A-Z].*)"))||!(password.matches("^(?=.*[_.()$&@]).*$")))
            {
                reg_v_password.requestFocus();
                reg_v_password.setError("FIELD CANNOT BE EMPTY/ 8 characters in length, 1 Uppercase Letter,1 Lowercase Letter, 1 Number, and no symbols");
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
            }
            else
            {
                Toast.makeText(User_Create_Account.this,"Validation Successful",Toast.LENGTH_LONG).show();
                UserAccountHelper helperClass = new UserAccountHelper(name, email, number, address,Postcode, password, verify_password);
                reference.child(reg_email.getText().toString()).setValue(helperClass);
                Intent a = new Intent(User_Create_Account.this, User_VerifyID_Screen.class);
                startActivity(a);
            }
        });

    }
}