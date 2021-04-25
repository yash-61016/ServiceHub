package uk.ac.tees.w9336459.servicehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServiceProviderLogin2 extends AppCompatActivity {


    // variable for FirebaseAuth class
    private FirebaseAuth mAuth;

    // variable for our text input
    // field for phone and OTP.
    private TextInputEditText emailid,password;

    // buttons for generating OTP and verifying OTP
    private Button splogin;

    TextView goTo_createAc, forgetpwd;

    private FirebaseAuth.AuthStateListener mAuthstatelistener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_login2);


        mAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.SP_SPL2loginTXT);
        password = findViewById(R.id.SP_SPL2passwordTXT);
        splogin = findViewById(R.id.SP_SPL2login);
        goTo_createAc = findViewById(R.id.Sp_LoginAc);
        forgetpwd = findViewById(R.id.sp_L_ForgetPassword);
        mAuthstatelistener = (firebaseAuth -> {
                FirebaseUser mFbuser = mAuth.getCurrentUser();
                if (mFbuser != null) {
                    Toast.makeText(ServiceProviderLogin2.this, "You are logged in!!", Toast.LENGTH_SHORT).show();
                    final String dp = getIntent().getStringExtra("downloadUrlPP");
                    Intent i = new Intent(ServiceProviderLogin2.this, ServiceProviderMainScreen.class);
                    i.putExtra("downloadUrlPP",dp);
                    startActivity(i);
                } else {
                    Toast.makeText(ServiceProviderLogin2.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            });
        splogin.setOnClickListener((v) -> {
                String email = emailid.getText().toString();
                String pwd = password.getText().toString();

                if (email.isEmpty()) {
                    emailid.setError("FIELD CANNOT BE EMPTY/INCORRECT EMAIL");
                    emailid.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("FIELD CANNOT BE EMPTY/INCORRECT PASSWORD");
                    password.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(ServiceProviderLogin2.this, "FIELDS ARE EMPTY!!!", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())||(email.matches(email))) {

                    mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(ServiceProviderLogin2.this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ServiceProviderLogin2.this, "Logged In Successfully!!", Toast.LENGTH_SHORT).show();
                            final String dp = getIntent().getStringExtra("downloadUrlPP");
                            Intent intToHome = new Intent(getApplicationContext(), ServiceProviderMainScreen.class);
                            intToHome.putExtra("downloadUrlPP",dp);
                            startActivity(intToHome);

                        } else {
                            Toast.makeText(ServiceProviderLogin2.this, "Error Occured! Please Login Again!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(ServiceProviderLogin2.this, "Error Occured!!", Toast.LENGTH_SHORT).show();
                }

            });

        goTo_createAc.setOnClickListener((v)->{
            Intent i = new Intent(ServiceProviderLogin2.this, ServiceProviderNewAccount.class);
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
                        Toast.makeText(ServiceProviderLogin2.this, "Reset Link Sent to Yourr Mail. " ,Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ServiceProviderLogin2.this,"Error ! Reset Link is not Sent" + e.getMessage(),Toast.LENGTH_LONG).show();

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
        protected void onStart() {
            super.onStart();
            //mAuth.addAuthStateListener(mAuthstatelistener);
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {
                currentUser.reload();
            }
    }
}
