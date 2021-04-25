package uk.ac.tees.w9336459.servicehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ServiceProviderNewAccountVerify extends AppCompatActivity {


    // variable for FirebaseAuth class
    private FirebaseAuth mAuth;

    // variable for our text input
    // field for phone and OTP.
    private EditText  edtOTP1;
    private TextView edtPhone;

    // buttons for generating OTP and verifying OTP
    private Button verifyOTPBtn, generateOTPBtn1;

    // string for storing our verification ID
    private String verificationId;

    String numberid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_new_account_verify);

        // below line is for getting instance
        // of our FirebaseAuth.
        mAuth = FirebaseAuth.getInstance();

        numberid = getIntent().getStringExtra("number");

        // initializing variables for button and Edittext.
        edtPhone = findViewById(R.id.SP_NAV_entenumber);
        edtOTP1 = findViewById(R.id.SP_NAV_NumberSendCode1);

        verifyOTPBtn = findViewById(R.id.SP_NAV_Done);
        generateOTPBtn1 = findViewById(R.id.SP_NAV_NumberSendCode);

        this.edtPhone.setText(numberid);




        // setting onclick listner for generate OTP button.
        generateOTPBtn1.setOnClickListener(v -> {
            // below line is for checking weather the user
            // has entered his mobile number or not.
            if (TextUtils.isEmpty(edtPhone.getText().toString())) {
                // when mobile number text field is empty
                // displaying a toast message.
                Toast.makeText(ServiceProviderNewAccountVerify.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
            } else {
                // if the text field is not empty we are calling our
                // send OTP method for getting OTP from Firebase.
                String phone = "+44" + edtPhone.getText().toString();
                sendVerificationCode(phone);
            }
        });


        // initializing on click listener
        // for verify otp button
        verifyOTPBtn.setOnClickListener(v -> {
            // validating if the OTP text field is empty or not.
            if (TextUtils.isEmpty(edtOTP1.getText().toString())) {
                // if the OTP text field is empty display
                // a message to user to enter OTP
                Toast.makeText(ServiceProviderNewAccountVerify.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
            } else {
                // if OTP field is not empty calling
                // method to verify the OTP.
                verifyCode(edtOTP1.getText().toString());
            }
        });
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // if the code is correct and the task is successful
                        // we are sending our user to new activity.

                        Bundle userbundle = getIntent().getExtras();
                        Intent i = new Intent(ServiceProviderNewAccountVerify.this, ProfileServiceProvider.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.putExtras(userbundle);
                        startActivity(i);
                        finish();

                    } else {
                        // if the code is not correct then we are
                        // displaying an error message to the user.
                        Toast.makeText(ServiceProviderNewAccountVerify.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }


    private void sendVerificationCode(String number) {
        // this method is used for getting
        // OTP on user phone number.
        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            // below method is used when
            // OTP is sent from Firebase
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                // when we receive the OTP it
                // contains a unique id which
                // we are storing in our string
                // which we have already created.
                verificationId = s;
            }

            // this method is called when user
            // receive OTP from Firebase.
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                // below line is used for getting OTP code
                // which is sent in phone auth credentials.
                final String code = phoneAuthCredential.getSmsCode();

                // checking if the code
                // is null or not.
                if (code != null) {
                    // if the code is not null then
                    // we are setting that code to
                    // our OTP edittext field.
                    edtOTP1.setText(code);

                    // after setting this code
                    // to OTP edittext field we
                    // are calling our verifycode method.
                    verifyCode(code);
                }
            }

            // this method is called when firebase doesn't
            // sends our OTP code due to any error or issue.
            @Override
            public void onVerificationFailed(FirebaseException e) {
                // displaying error message with firebase exception.
                Toast.makeText(ServiceProviderNewAccountVerify.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        };
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number) // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }


    // below method is use to verify code from Firebase.
    private void verifyCode(String code) {
        // below line is used for getting getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }
}
