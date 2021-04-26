package uk.ac.tees.w9336459.servicehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    private EditText email;
    private Button reset;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        email=findViewById(R.id.editText);
        reset=findViewById(R.id.button);
        firebaseAuth=FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String useremail=email.getText().toString().trim();

                if(useremail.equals("")){
                    Toast.makeText(ResetPassword.this,"please enter your registered emailID",Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ResetPassword.this,"Password reset email sent",Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(ResetPassword.this,ServiceProviderLogin2.class));
                            }
                            else
                            {
                                Toast.makeText(ResetPassword.this,"Error in sending password reset email. Use registered Email",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
    }