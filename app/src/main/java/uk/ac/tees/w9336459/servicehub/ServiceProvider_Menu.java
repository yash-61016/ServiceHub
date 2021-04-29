package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class ServiceProvider_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider__menu);
    }
    public void logout(View v){
        final AlertDialog.Builder builder=new AlertDialog.Builder(ServiceProvider_Menu.this);
        builder.setMessage("Do you want to Logout ?");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(ServiceProvider_Menu.this,MainActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

            }
        });
        builder.setPositiveButton("No",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface,int i){

                dialogInterface.cancel();
            }

        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
    public void contact(View v){
        Intent intent=new Intent(ServiceProvider_Menu.this,contact_us.class);
        startActivity(intent);

    }
    public void reset(View v)
    {
        Intent intent=new Intent(ServiceProvider_Menu.this,ResetPassword.class);
        startActivity(intent);

    }
    public void changelocation(View v)
    {
        Intent intent = new Intent(ServiceProvider_Menu.this,ChangeLocationSP.class);
        startActivity(intent);
    }
    public void servicehistory(View v){
        Intent intent = new Intent(ServiceProvider_Menu.this,RequestStatus.class);
        intent.putExtra("buttontext","Completed Requests");
        startActivity(intent);

    }
}