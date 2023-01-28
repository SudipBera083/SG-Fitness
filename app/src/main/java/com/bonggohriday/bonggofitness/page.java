package com.bonggohriday.bonggofitness;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;


public class page extends AppCompatActivity {
    EditText cat;
    EditText desc;
    Button sub;
    ImageView img;
    Button add;
    FirebaseDatabase database;
    Uri imageUri;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    String filename;
    Button next;



    ActivityResultLauncher <String> launcher;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bonggofitness2-default-rtdb.asia-southeast1.firebasedatabase.app");


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        cat = findViewById(R.id.heading);
        desc = findViewById(R.id.description);
        sub = findViewById(R.id.submit);
        img = findViewById(R.id.upload_img);
        add = findViewById(R.id.upload1);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,100);



            }
        });



        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final  String heading = cat.getText().toString();
               final  String description = desc.getText().toString();

                if(TextUtils.isEmpty(heading))
                {
                    cat.setText("Email cannot be empty");
                    cat.requestFocus();
                }
                else if(TextUtils. isEmpty(description))
                {
                    desc.setText("Password cannot be empty");
                    desc.requestFocus();
                }
                else
                {
                    databaseReference.child("data").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild("data/category"))
                            {
                                Toast.makeText(page.this,"This category is already Exists!",Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                databaseReference.child("data").child(heading).child("description").setValue(description);
                                String file = addImg();

                                databaseReference.child("data").child(heading).child("img_no").setValue(file);
                                Toast.makeText(page.this, "Successfully added",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });


    }

    private String addImg() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading the files....");
        progressDialog.show();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH__mm_ss");
        Date now = new Date();
        String filename = formatter.format(now);

        storageReference = FirebaseStorage.getInstance().getReference("images/"+filename);
        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        img = findViewById(R.id.upload_img);
                        img.setImageURI(null);
                        Toast.makeText(page.this,"Successfully Uploaded!",Toast.LENGTH_SHORT).show();
                        if(progressDialog.isShowing())
                        {
                            progressDialog.dismiss();
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(progressDialog.isShowing())
                        {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(page.this,"Some Error occurred!",Toast.LENGTH_SHORT).show();

                    }
                });

        return filename;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100 && data!= null && data.getData()!=null)
        {
                imageUri= data.getData();
                img = findViewById(R.id.upload_img);
                img.setImageURI(imageUri);
        }
    }
}