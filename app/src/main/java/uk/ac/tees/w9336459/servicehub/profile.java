package uk.ac.tees.w9336459.servicehub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.util.Base64;
import android.util.LruCache;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ThreadPoolExecutor;

import javax.xml.transform.Result;

import de.hdodenhof.circleimageview.CircleImageView;

import static uk.ac.tees.w9336459.servicehub.U_MainScreen.decodeUserEmail;


public class profile extends AppCompatActivity {

    static private CircleImageView profileimageview ;
    private Button details ,Log_out , changepassword;
    private TextView profilename;

    private  DatabaseReference mref;
    private FirebaseAuth mAuth;

    private Uri filePath1;
    Uri downloadUrlPP;
    private String myUri = "",  email = "";

    //private StorageTask uploadtask;
    private StorageReference storageReference;

    private  Button buttonChoose1,buttonUpload1;
    static int count=0;


    private static final int PICK_IMAGE_REQUEST = 234;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = decodeUserEmail(user.getEmail());
        mAuth = FirebaseAuth.getInstance();
        mref = FirebaseDatabase.getInstance().getReference().child("Users").child("Details").child(email);
        storageReference = FirebaseStorage.getInstance().getReference().child("ProfilePic");

        buttonChoose1 =  findViewById(R.id.chooser1);
        buttonUpload1 =  findViewById(R.id.uploader1);

        profileimageview =  findViewById(R.id.circle_image);
        profilename = findViewById(R.id.ProfileName);

        changepassword = findViewById(R.id.changpassword);
        details = findViewById(R.id.Details);
        Log_out = findViewById(R.id.logout);

        String EmailChildID = decodeUserEmail(user.getEmail());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child("Details").child(EmailChildID);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Read from the database
                String fname = dataSnapshot.child("firstname").getValue(String.class);
                String lname = dataSnapshot.child("lastname").getValue(String.class);
                profilename.setText(fname+" "+lname);

                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    if(dataSnapshot.child("image").getValue().toString().equals("null")){
                        Picasso.get().load(R.drawable.avatar).into(profileimageview);
                    }
                    else  {
                        String image = dataSnapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profileimageview);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        Log_out.setOnClickListener((V)->{

            final AlertDialog.Builder builder=new AlertDialog.Builder(profile.this);
            builder.setMessage("Do you want to Logout ?");
            builder.setCancelable(true);
            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent=new Intent(profile.this,welcome.class);
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


        buttonChoose1.setOnClickListener((v)->{
            showFileChooser();
        });
        buttonUpload1.setOnClickListener((v)->{
            uploadFile1();
        });

        details.setOnClickListener((v)->{

            Intent User_details = new Intent(profile.this, User_Details.class);
            startActivity(User_details);
            finish();
        });

        changepassword.setOnClickListener((v)->{

            final TextInputEditText resetmail = new TextInputEditText(v.getContext());
            final androidx.appcompat.app.AlertDialog.Builder pwdResetDialog = new androidx.appcompat.app.AlertDialog.Builder(v.getContext());
            pwdResetDialog.setTitle("Reset Password?");
            pwdResetDialog.setMessage("Enter the Email to Recieve a Reset Link");
            pwdResetDialog.setView(resetmail);

            pwdResetDialog.setPositiveButton("Yes", (dialogInterface, i) -> {
                // extract the link and send the email

                String mail = resetmail.getText().toString();
                mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(profile.this, "Reset Link Sent to Yourr Mail. " ,Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(profile.this,"Error ! Reset Link is not Sent" + e.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });
            }) ;

            pwdResetDialog.setNegativeButton("No", (dialogInterface, i) -> {
                // close the dialog
            });
            pwdResetDialog.create().show();

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

                    if(snapshot.hasChild("Image")){
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

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath1 = data.getData();
            try {
                Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath1);
                profileimageview.setImageBitmap(bitmap1);
                Toast.makeText(profile.this,"Please upload the Picture",Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(requestCode == RESULT_CANCELED){

            Toast.makeText(profile.this,"Failed to upload the Picture",Toast.LENGTH_LONG).show();

        }
    }
    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    private void uploadFile1() {
        //if there is a file to upload
        if (filePath1 != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            final StorageReference profileRef = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(filePath1));
            profileRef.putFile(filePath1)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successful
                            //hiding the progress dialog

                            progressDialog.dismiss();
                            profileimageview.setImageResource(0);

                            Toast.makeText(profile.this, "Uploaded..", Toast.LENGTH_SHORT).show();
                            Toast.makeText(profile.this, "Please Restart the app to see the changes", Toast.LENGTH_SHORT).show();
                            buttonChoose1.setClickable(false);
                            buttonUpload1.setClickable(false);
                            //and displaying a success toast
                            profileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                                downloadUrlPP = uri;
                                count++;

                              // Picasso.get().load(downloadUrlPP.toString()).into(profileimageview);
                            });


                            mref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                                    Customers c = datasnapshot.getValue(Customers.class);
                                    c.setImage(downloadUrlPP.toString());
                                    mref.setValue(c);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }})
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();


                            //and displaying error message
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress=0;

                            progress = (double) (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");



                        }

                    });

        }
        //if there is not any file
        else {
            //you can display an error toast
            Toast.makeText(getApplicationContext(), "No file selected", Toast.LENGTH_SHORT).show();
        }
    }



    // this is the older version of profile picture build , but it uses 3 party system like Google Account setUp
    /**

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data!= null)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            uri = result.getUri();
            profileimageview.setImageURI(uri);
            uploadProfileImage();
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
           final StorageReference fileRef = strProfileRef.child(mAuth.getCurrentUser().getUid()+".jpg");

           uploadtask = fileRef.putFile(uri);

           uploadtask.continueWithTask(new Continuation() {
               @Override
               public Object then(@NonNull Task task) throws Exception {
                   if(!task.isSuccessful()){
                       throw  task.getException();
                   }
                   return fileRef.getDownloadUrl();
               }
           })
                   .addOnCompleteListener((OnCompleteListener<Uri>) task -> {
                       if (task.isSuccessful()){
                           Uri downloadUrl = task.getResult();
                           myUri = downloadUrl.toString();

                          // HashMap<String, Object> userMap = new HashMap<>();
                          // userMap.put("image",myUri);

                           mref.child("image").setValue(myUri);
                           pd.dismiss();

                       }
                   });
        }else{
            pd.dismiss();
            Toast.makeText(this, "Image not selected", Toast.LENGTH_SHORT).show();
        }
    }
    **/


    @Override
    public void onBackPressed(){
    startActivity(new Intent(this, U_MainScreen.class));
    }


}
