package com.bonggohriday.bonggofitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class logIn extends AppCompatActivity {

    EditText phone12;
    EditText pass12;
    Button btn2;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bonggofitness2-default-rtdb.asia-southeast1.firebasedatabase.app");


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        phone12 = findViewById(R.id.email_logIn);
        pass12 = findViewById(R.id.pass_logIn);
        btn2 =  findViewById(R.id.btn_logIn);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phone = phone12.getText().toString();
                final  String pass = pass12.getText().toString();

                if(TextUtils.isEmpty(phone))
                {
                    phone12.setText("Email cannot be empty");
                    phone12.requestFocus();
                }
                else if(TextUtils. isEmpty(pass))
                {
                    pass12.setText("Password cannot be empty");
                    pass12.requestFocus();
                }
                else
                {
                    if(phone.equals("Bonggo12@"))
                    {
                        databaseReference.child("admin").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild("id"))
                                {
                                    final  String password = snapshot.child("password").getValue(String.class);
                                    if(password.equals(pass))
                                    {
                                        startActivity(new Intent(logIn.this, dashboard.class));
                                    }
                                    else {
                                        Toast.makeText(logIn.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                    else {
                        Toast.makeText(logIn.this, "Invalid Credentials",Toast.LENGTH_SHORT).show();
                    }
//                    else {
//
//
//                        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                if (snapshot.hasChild(phone)) {
//                                    final String getPass = snapshot.child(phone).child("password").getValue(String.class);
//                                    if (getPass.equals(pass)) {
//                                        startActivity(new Intent(logIn.this, user_page_1.class));
//                                    } else {
//                                        Toast.makeText(logIn.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//                    }
                }


            }
        });

    }
}