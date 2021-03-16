package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ServiceProviderLogin extends AppCompatActivity {

    Button newAccount,login,skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_login);
        newAccount = findViewById(R.id.SP_SPLcreateAccount);
        login = findViewById(R.id.SP_SPLlogin);
        skip = findViewById(R.id.SP_SPLskip);

        newAccount.setOnClickListener((v)->{

            Intent a = new Intent(ServiceProviderLogin.this , ServiceProviderNewAccount.class);
            startActivity(a);

        });
        login.setOnClickListener((v)->{

            Intent a = new Intent(ServiceProviderLogin.this , ServiceProviderLogin2.class);
            startActivity(a);

        });
        skip.setOnClickListener((v)->{

            Intent a = new Intent(ServiceProviderLogin.this , ServiceProviderMainScreen.class);
            startActivity(a);

        });
    }
}