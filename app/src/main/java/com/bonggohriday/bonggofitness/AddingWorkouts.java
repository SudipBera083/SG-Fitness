package com.bonggohriday.bonggofitness;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddingWorkouts extends AppCompatActivity {
    Button add;
    EditText category;

    EditText workout_name;
    EditText wk_desc;
    Uri imageUri1;
    ImageView img;
    Button submit;
    ProgressDialog progressDialog;
    StorageReference storageReference;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bonggofitness2-default-rtdb.asia-southeast1.firebasedatabase.app");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_workouts);
        category = findViewById(R.id.category_name);
        workout_name = findViewById(R.id.workout_name);
        wk_desc = findViewById(R.id.desc_workout);
        img = findViewById(R.id.pf);
        submit = findViewById(R.id.sub_workout);


        add = findViewById(R.id.add_img_wk);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 100);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String cat = category.getText().toString();
                final String wk_name = workout_name.getText().toString();
                final String wk_desc_1 = wk_desc.getText().toString();

                if (TextUtils.isEmpty(cat)) {
                    category.setText("Field cannot be empty");
                    category.requestFocus();
                } else if (TextUtils.isEmpty(wk_desc_1)) {
                    wk_desc.setText("Field cannot be empty");
                    wk_desc.requestFocus();
                } else if (TextUtils.isEmpty(wk_name)) {
                    workout_name.setText("Field cannot be empty");
                    workout_name.requestFocus();
                } else {

                    databaseReference.child("data").child(cat).child("workout").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild("data/category/workout")) {
                                Toast.makeText(AddingWorkouts.this, "This category is already Exists!", Toast.LENGTH_SHORT).show();

                            } else {
                                databaseReference.child("data").child(cat).child("workout").child(wk_name).child("name").setValue(wk_name);

                                databaseReference.child("data").child(cat).child("workout").child(wk_name).child("description").setValue(wk_desc_1);
                                final String file = addImg();

//                                CHANGING THE DATA

                                databaseReference.child("data").child(cat).child("workout").child(wk_name).child("img_no").setValue(file);
                                Toast.makeText(AddingWorkouts.this, "Successfully added", Toast.LENGTH_SHORT).show();



//                                ====================================================================























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

        storageReference = FirebaseStorage.getInstance().getReference("workouts/" + filename);

        storageReference.putFile(imageUri1)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        img = findViewById(R.id.pf);
                        img.setImageURI(null);
                        Toast.makeText(AddingWorkouts.this, "Successfully Uploaded!", Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(AddingWorkouts.this, "Some Error occurred!", Toast.LENGTH_SHORT).show();

                    }
                });

        return filename;

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null) {
            imageUri1 = data.getData();
            img = findViewById(R.id.pf);
            img.setImageURI(imageUri1);
        }
    }
}
