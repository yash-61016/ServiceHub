package uk.ac.tees.w9336459.servicehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class User_Login extends AppCompatActivity {

    // variable for FirebaseAuth class
    private FirebaseAuth mAuth;

    // variable for our text input
    // field for phone and OTP.
    private TextInputEditText emailid,password;

    // buttons for generating OTP and verifying OTP
    private Button login;

    TextView goTo_createAc,forgetpwd;


    private FirebaseAuth.AuthStateListener mAuthstatelistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__login);

        mAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.U_L_loginid);
        password = findViewById(R.id.U_L_passwordenter);
        login = findViewById(R.id.U_L_LoginInbt);
        goTo_createAc = findViewById(R.id.U_LoginAc);
        forgetpwd = findViewById(R.id.U_L_ForgetPassword);

        mAuthstatelistener = (firebaseAuth -> {
            FirebaseUser mFbuser = mAuth.getCurrentUser();
            if (mFbuser != null) {
                Toast.makeText(User_Login.this, "You are logged in!!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(User_Login.this, U_MainScreen.class);
                startActivity(i);
            } else {
                Toast.makeText(User_Login.this, "Please Login", Toast.LENGTH_SHORT).show();
            }
        });


        login.setOnClickListener((v) -> {
            String email = emailid.getText().toString();
            String pwd = password.getText().toString();

            if (email.isEmpty()) {
                emailid.setError("FIELD CANNOT BE EMPTY/INCORRECT EMAIL");
                emailid.requestFocus();
            } else if (pwd.isEmpty()) {
                password.setError("FIELD CANNOT BE EMPTY/INCORRECT PASSWORD");
                password.requestFocus();
            } else if (email.isEmpty() && pwd.isEmpty()) {
                emailid.setError("FIELD CANNOT BE EMPTY/INCORRECT EMAIL");
                emailid.requestFocus();
                password.setError("FIELD CANNOT BE EMPTY/INCORRECT PASSWORD");
                password.requestFocus();
                Toast.makeText(User_Login.this, "FIELDS ARE EMPTY!!!", Toast.LENGTH_SHORT).show();
            } else if (!(email.isEmpty() && pwd.isEmpty())) {


                mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(User_Login.this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(User_Login.this, "Logged In Successfully!!", Toast.LENGTH_SHORT).show();
                        Intent intToHome = new Intent(getApplicationContext(), U_MainScreen.class);
                        startActivity(intToHome);
                        finish();

                    } else {
                        Toast.makeText(User_Login.this, "Error Occured! Please Login Again!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(User_Login.this, "Error Occured!!", Toast.LENGTH_SHORT).show();
            }

        });

        goTo_createAc.setOnClickListener((v)->{
            Intent i = new Intent(User_Login.this, User_Create_Account.class);
            startActivity(i);
            finish();

        });

        forgetpwd.setOnClickListener((V)->{

            final TextInputEditText resetmail = new TextInputEditText(V.getContext());
            final AlertDialog.Builder pwdResetDialog = new AlertDialog.Builder(V.getContext());
            pwdResetDialog.setTitle("Reset Password?");
            pwdResetDialog.setMessage("Enter the Email to Recieve a Reset Link");
            pwdResetDialog.setView(resetmail);

            pwdResetDialog.setPositiveButton("Yes", (dialogInterface, i) -> {
                // extract the link and send the email

                String mail = resetmail.getText().toString();
                mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(User_Login.this, "Reset Link Sent to Yourr Mail. " ,Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(User_Login.this,"Error ! Reset Link is not Sent" + e.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });


            }) ;

            pwdResetDialog.setNegativeButton("No", (dialogInterface, i) -> {
                // close the dialog
            });
            pwdResetDialog.create().show();

        });

    }
    @Override
    protected void onStart(){
        super.onStart();
        //mAuth.addAuthStateListener(mAuthstatelistener);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

}