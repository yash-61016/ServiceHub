package uk.ac.tees.w9336459.servicehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import uk.ac.tees.w9336459.servicehub.Model.ServiceProviders2;
import uk.ac.tees.w9336459.servicehub.ServiceProviderMainScreen.*;

import java.io.IOException;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileServiceProvider<findView>  extends AppCompatActivity implements View.OnClickListener, OnItemSelectedListener {



    private static final int PICK_IMAGE_REQUEST = 234;

    private Button buttonChoose1,buttonUpload1;
    private Button next;
    private TextView chose;
    private EditText description;
    private CircleImageView imageView1;
    Spinner skillspinner;
    Uri downloadUrlPP;
    private StorageReference storageReference;
    private Uri filePath1;
    static int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_service_provider);

        storageReference= FirebaseStorage.getInstance().getReference("SP_ProfilePic");
        buttonChoose1 =  findViewById(R.id.chooser1);
        buttonUpload1 =  findViewById(R.id.uploader1);
        description = findViewById(R.id.SP_Description);

        next = findViewById(R.id.profilenext_button);



        imageView1 = findViewById(R.id.imageView1);
        chose=findViewById(R.id.chose);
        buttonChoose1.setOnClickListener(this);
        buttonUpload1.setOnClickListener(this);

        next.setOnClickListener(this);


        skillspinner = (Spinner) findViewById(R.id.skillsTXT);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.skills, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skillspinner.setAdapter(adapter);
        skillspinner.setOnItemSelectedListener(this);

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
                chose.setText("Your chosen image is shown");
                imageView1.setImageBitmap(bitmap1);

            } catch (IOException e) {
                e.printStackTrace();
            }
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
                            imageView1.setImageResource(0);
                            chose.setText("Choose your image");
                            Toast.makeText(ProfileServiceProvider.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            buttonChoose1.setClickable(false);
                            buttonUpload1.setClickable(false);
                            buttonUpload1.setBackgroundColor(Color.GRAY);
                            buttonChoose1.setBackgroundColor(Color.GRAY);


                            //and displaying a success toast
                            profileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                                downloadUrlPP = uri;
                                count++;
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


    @Override
    public void onClick(View view) {
        if(view == next)
        {


            if(count<1)
                Toast.makeText(ProfileServiceProvider.this, "Upload all required files",Toast.LENGTH_SHORT).show();
            else if (skillspinner.getSelectedItem().toString().matches("skills"))
            {

                Toast toast = Toast.makeText(getApplicationContext(),
                    "Select Skills",
                    Toast.LENGTH_LONG);
                toast.show();
            }
            else {

                Bundle b = getIntent().getExtras();
                b.putString("downloadUrlPP",downloadUrlPP.toString());
                b.putString("skills",skillspinner.getSelectedItem().toString());
                b.putString("description",description.getText().toString());
                Intent intent = new Intent(ProfileServiceProvider.this, asklocationSP.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        }
        //if the clicked button is choose
        if (view == buttonChoose1) {
            showFileChooser();
        }
        //if the clicked button is upload
        else if (view == buttonUpload1) {
            uploadFile1();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}





