package com.bonggohriday.bonggofitness;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import pl.droidsonroids.gif.GifImageView;

public class user_page_1 extends AppCompatActivity {
    String chest_img;
    String  back_img;
    String biceps_img;
    String triceps_img;
    String shoulder_img;
    String leg_img;
    String glute_img;
    String forearms_img;
    Uri imageUri;

    GoogleSignInClient mGoogleSignInClient;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page1);



        TextView logout =findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });


        Button chest =findViewById(R.id.chest);
        chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(user_page_1.this, Chest_workouts.class));
            }
        });

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(user_page_1.this, Back_Workouts.class));
            }
        });


        Button biceps = findViewById(R.id.biceps);
        biceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(user_page_1.this, Biceps_Workouts.class));
            }
        });


        Button triceps = findViewById(R.id.triceps);
        triceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(user_page_1.this, Triceps_workouts.class));
            }
        });

        Button shoulder =findViewById(R.id.shoulder);
        shoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(user_page_1.this, Shoulder_Workouts.class));
            }
        });


        Button leg = findViewById(R.id.leg);
        leg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(user_page_1.this, Leg_workouts.class));
            }
        });


        Button glute = findViewById(R.id.glute);
        glute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(user_page_1.this, Glute_Workouts.class));
            }
        });

        Button forearms = findViewById(R.id.forearms);
        forearms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(user_page_1.this, ForeArms_workouts.class));
            }
        });




        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bonggofitness2-default-rtdb.asia-southeast1.firebasedatabase.app");

       databaseReference.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
               databaseReference.child("data").addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       String key1="";
                       for(DataSnapshot childsnapsort: snapshot.getChildren())
                       {
                           String key = childsnapsort.getKey();
                           if(key.equals("chest"))
                           {
                               String desc = snapshot.child(key).child("description").getValue(String.class);
                               TextView c =findViewById(R.id.chest_desc);
                               c.setText(desc);
                               chest_img =snapshot.child(key).child("img_no").getValue(String.class);

                               StorageReference  storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://bonggofitness2.appspot.com/images/"+chest_img);

                               try {
                                   File localFile = File.createTempFile("tempGif", ".gif");
                                   storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                       @Override
                                       public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//

                                           ImageView i = findViewById(R.id.c_img);
                                           // Adding the gif here using glide library
                                           Glide.with(user_page_1.this).load(localFile.getAbsolutePath()).into(i);
                                       }
                                   });



                               } catch (IOException e) {
                                   e.printStackTrace();
                               }


                           }
                           else if(key.equals("back"))
                           {
                               String desc = snapshot.child(key).child("description").getValue(String.class);
                               TextView b =findViewById(R.id.back_desc);
                               b.setText(desc);
                               back_img =snapshot.child(key).child("img_no").getValue(String.class);


                               StorageReference  storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://bonggofitness2.appspot.com/images/"+back_img);

                               try {
                                   File localFile = File.createTempFile("tempGif", ".gif");
                                   storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                       @Override
                                       public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                           ImageView i = findViewById(R.id.back_id);
                                           Glide.with(user_page_1.this).load(localFile.getAbsolutePath()).into(i);

                                       }
                                   });



                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                           }

                           else if(key.equals("biceps"))
                           {
                               String desc = snapshot.child(key).child("description").getValue(String.class);
                               TextView b =findViewById(R.id.biceps_desc);
                               b.setText(desc);
                               biceps_img =snapshot.child(key).child("img_no").getValue(String.class);

                               StorageReference  storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://bonggofitness2.appspot.com/images/"+biceps_img);

                               try {
                                   File localFile = File.createTempFile("tempGif", ".gif");
                                   storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                       @Override
                                       public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                           ImageView i = findViewById(R.id.biceps_id);
                                           Glide.with(user_page_1.this).load(localFile.getAbsolutePath()).into(i);
                                       }
                                   });



                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                           }
                           else if(key.equals("triceps"))
                           {
                               String desc = snapshot.child(key).child("description").getValue(String.class);
                               TextView t =findViewById(R.id.triceps_desc);
                               t.setText(desc);
                               triceps_img =snapshot.child(key).child("img_no").getValue(String.class);

                               StorageReference  storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://bonggofitness2.appspot.com/images/"+triceps_img);

                               try {
                                   File localFile = File.createTempFile("tempGif", ".gif");
                                   storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                       @Override
                                       public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                           ImageView i = findViewById(R.id.tr_id);
                                           Glide.with(user_page_1.this).load(localFile.getAbsolutePath()).into(i);
                                       }
                                   });



                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                           }
                           else if(key.equals("shoulder"))
                           {
                               String desc = snapshot.child(key).child("description").getValue(String.class);
                               TextView s =findViewById(R.id.shoulder_desc);
                               s.setText(desc);
                               shoulder_img =snapshot.child(key).child("img_no").getValue(String.class);

                               StorageReference  storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://bonggofitness2.appspot.com/images/"+shoulder_img);

                               try {
                                   File localFile = File.createTempFile("tempGif", ".gif");
                                   storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                       @Override
                                       public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                           ImageView i = findViewById(R.id.shoulder_id);
                                           Glide.with(user_page_1.this).load(localFile.getAbsolutePath()).into(i);
                                       }
                                   });



                               } catch (IOException e) {
                                   e.printStackTrace();
                               }


                           }
                           else if(key.equals("leg"))
                           {
                               String desc = snapshot.child(key).child("description").getValue(String.class);
                               TextView l =findViewById(R.id.leg_desc);
                               l.setText(desc);
                               leg_img =snapshot.child(key).child("img_no").getValue(String.class);


                               StorageReference  storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://bonggofitness2.appspot.com/images/"+leg_img);

                               try {
                                   File localFile = File.createTempFile("tempGif", ".gif");
                                   storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                       @Override
                                       public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                           ImageView i = findViewById(R.id.leg_id);
                                           Glide.with(user_page_1.this).load(localFile.getAbsolutePath()).into(i);
                                       }
                                   });



                               } catch (IOException e) {
                                   e.printStackTrace();
                               }


                           }

                           else if(key.equals("glute"))
                           {
                               String desc = snapshot.child(key).child("description").getValue(String.class);
                               TextView g =findViewById(R.id.glute_desc);
                               g.setText(desc);
                               glute_img =snapshot.child(key).child("img_no").getValue(String.class);

                               StorageReference  storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://bonggofitness2.appspot.com/images/"+glute_img);

                               try {
                                   File localFile = File.createTempFile("tempGif", ".gif");
                                   storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                       @Override
                                       public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                           ImageView i = findViewById(R.id.glute_id);
                                           Glide.with(user_page_1.this).load(localFile.getAbsolutePath()).into(i);
                                       }
                                   });



                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                           }

                           else if(key.equals("forearms"))
                           {
                               String desc = snapshot.child(key).child("description").getValue(String.class);
                               TextView f=findViewById(R.id.forearms_desc);
                               f.setText(desc);
                               forearms_img =snapshot.child(key).child("img_no").getValue(String.class);

                               StorageReference  storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://bonggofitness2.appspot.com/images/"+forearms_img);

                               try {
                                   File localFile = File.createTempFile("tempGif", ".gif");
                                   storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                       @Override
                                       public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                         ;
                                           ImageView i = findViewById(R.id.forearms_id);
                                           Glide.with(user_page_1.this).load(localFile.getAbsolutePath()).into(i);
                                       }
                                   });



                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                           }

                       }

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
           }

           @Override
           public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

           }

           @Override
           public void onChildRemoved(@NonNull DataSnapshot snapshot) {

           }

           @Override
           public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(user_page_1.this, googleSingUp.class));
                    }
                });
    }


}