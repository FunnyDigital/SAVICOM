package com.bitvilltecnologies.savicom.HOMEFUNCTION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bitvilltecnologies.savicom.MODELS.Add_lost_item_Model;
import com.bitvilltecnologies.savicom.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class AddLost extends AppCompatActivity {

    Button  add;
    EditText lost_date, lost_des ;
    ImageButton lost_imageBtn;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;
    String profileImageurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lost);

        storageReference = FirebaseStorage.getInstance().getReference("LostItem");
        databaseReference = FirebaseDatabase.getInstance().getReference("LostItem");

        lost_date=(EditText)findViewById(R.id.addlost_date);
        lost_des=(EditText)findViewById(R.id.addlost_des);
        lost_imageBtn=(ImageButton)findViewById(R.id.addlost_imagebtn);
        add=(Button)findViewById(R.id.lost_addbtn);
        ///////////////////////////////////////////////


        lost_imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                UploadImage();

            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                lost_imageBtn.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    public void UploadImage() {

        if (FilePathUri != null) {

            //progressDialog.setTitle("Image is Uploading...");
           // progressDialog.show();
            StorageReference storageReference2 =  storageReference.child(System.currentTimeMillis()+"."+GetFileExtension(FilePathUri));

            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(
                                    new OnCompleteListener<Uri>() {

                                        @Override
                                        public void onComplete(@NonNull Task<Uri> task) {
                                            String  profileImageurl = task.getResult().toString();
                                            //next work with URL
                                     Toast.makeText(AddLost.this,"got url"+task,Toast.LENGTH_SHORT).show();

                                            String lostdate = lost_date.getText().toString().trim();
                                            String lostdes = lost_des.getText().toString().trim();

                                            //progressDialog.dismiss();
                                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
                                            @SuppressWarnings("VisibleForTests")
                                            Add_lost_item_Model imageUploadInfo = new Add_lost_item_Model(profileImageurl,lostdate,lostdes);

                                            String ImageUploadId = databaseReference.push().getKey();
                                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                                        }
                                    });


                        }
                    });
        }
        else {

            Toast.makeText(AddLost.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }






}