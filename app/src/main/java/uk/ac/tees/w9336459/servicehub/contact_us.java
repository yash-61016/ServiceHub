package uk.ac.tees.w9336459.servicehub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class contact_us extends AppCompatActivity {

    Button call,email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        call=findViewById(R.id.callus);
        email=findViewById(R.id.emailus);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7388307668"));
                startActivity(intent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","jatinaneja2000@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "ServiceHub Support");
                emailIntent.putExtra(Intent.EXTRA_TEXT, " ");
                startActivity(Intent.createChooser(emailIntent, "Send email"));
            }
        });
    }

}



