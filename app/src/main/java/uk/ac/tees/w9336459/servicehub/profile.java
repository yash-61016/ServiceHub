package uk.ac.tees.w9336459.servicehub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.util.Base64;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ThreadPoolExecutor;

import de.hdodenhof.circleimageview.CircleImageView;


public class profile extends AppCompatActivity {

    static private CircleImageView profileimageview , createimage;
    private Button details ,Log_out , wallet;
    private TextView profilename;

    private  DatabaseReference mref;
    private FirebaseAuth mAuth;

    private Uri uri;
    private String myUri = "",  email = "";

    private StorageTask uploadtask;
    private StorageReference strProfileRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = U_MainScreen.decodeUserEmail(user.getEmail());
        mAuth = FirebaseAuth.getInstance();
        mref = FirebaseDatabase.getInstance().getReference().child("Users").child("Details");
        strProfileRef = FirebaseStorage.getInstance().getReference().child("Profile Pic");

        profileimageview =  findViewById(R.id.circle_image);
        profilename = findViewById(R.id.ProfileName);
        createimage = findViewById(R.id.changeimage);
        details = findViewById(R.id.Details);
        Log_out = findViewById(R.id.logout);


        createimage.setOnClickListener((v)->{
            uploadProfileImage();
            CropImage.activity().setAspectRatio(1,1).start(profile.this);


        });


        Log_out.setOnClickListener((V)->{

            final AlertDialog.Builder builder=new AlertDialog.Builder(profile.this);
            builder.setMessage("Do you want to Logout ?");
            builder.setCancelable(true);
            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent=new Intent(profile.this,User_Login.class);
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


        });



        details.setOnClickListener((v)->{

            Intent User_details = new Intent(profile.this, User_Details.class);
            startActivity(User_details);
            getParent().finish();
        });

        getUserinfo();

    }

    private void getUserinfo() {
        mref.child(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() && snapshot.getChildrenCount()>0){
                    String name = snapshot.child("name").getValue(String.class);
                    profilename.setText(name);

                    if(snapshot.hasChild("image")){
                        String image = snapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profileimageview);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data!= null)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            uri = result.getUri();

            profileimageview.setImageURI(uri);
        }else{
            Toast.makeText(profile.this,"Error Loading the Image!! , Try Again!", Toast.LENGTH_LONG).show();
        }
    }

    private void uploadProfileImage() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Set your profile");
        pd.setMessage("Please wait, while we are setting your data...");
        pd.show();

        if(uri != null){
           final StorageReference fileRef = strProfileRef
           .child(mAuth.getCurrentUser().getUid()+".jpg");

           uploadtask = fileRef.putFile(uri);

           uploadtask.continueWithTask(new Continuation() {
               @Override
               public Object then(@NonNull Task task) throws Exception {
                   if(!task.isSuccessful()){
                       throw  task.getException();
                   }
                   return fileRef.getDownloadUrl();
               }
           }).addOnCompleteListener(new OnCompleteListener<Uri>() {
               @Override
               public void onComplete(@NonNull Task<Uri> task) {
                   if (task.isSuccessful()){
                       Uri downloadUrl = task.getResult();
                       myUri = downloadUrl.toString();

                       HashMap<String, Object> userMap = new HashMap<>();
                       userMap.put("image",myUri);

                       mref.child(email).updateChildren(userMap);

                       pd.dismiss();

                   }
               }
           });
        }else{
            pd.dismiss();
            Toast.makeText(this, "Image not selected", Toast.LENGTH_SHORT).show();
        }
    }

}
