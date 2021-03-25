package uk.ac.tees.w9336459.servicehub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class profile extends AppCompatActivity {
    int SELECT_PHOTO=1;
    Uri uri;
    ImageView imageView , rollback;
    Button Log_out;
    private FirebaseStorage storage;
    private StorageReference myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imageView=findViewById(R.id.circle_image);
        rollback = findViewById(R.id.roll_back);
        Log_out = findViewById(R.id.logout);

        storage = FirebaseStorage.getInstance();
        myref = storage.getReference();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,SELECT_PHOTO);
                finish();
            }
        });

        rollback.setOnClickListener((V)->{
            Intent i = new Intent(profile.this,U_MainScreen.class );
            startActivity(i);
            finish();
        });

        Log_out.setOnClickListener((V)->{

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), UserSignUp.class));
            finish();

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_PHOTO && resultCode==RESULT_OK && data!=null && data.getData()!=null ){
            uri=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imageView.setImageBitmap(bitmap);
                UploadPicture();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void UploadPicture() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image.....");
        pd.show();

        final String randamkey = UUID.randomUUID().toString();
        StorageReference ref = myref.child("image/"+ randamkey);

        ref.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Snackbar.make(findViewById(android.R.id.content), "Image Uploaded..",Snackbar.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(),"Failed to upload", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
