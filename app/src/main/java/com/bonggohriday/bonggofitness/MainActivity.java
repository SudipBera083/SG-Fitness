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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText email1;
    EditText pass1;
    EditText name1;
    EditText phone1;
    TextView loginto;


   DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bonggofitness2-default-rtdb.asia-southeast1.firebasedatabase.app");


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email1 = findViewById(R.id.email1);
        pass1 = findViewById(R.id.pass1);
        btn = findViewById(R.id.btn1);
        name1 = findViewById(R.id.personName);
        phone1 = findViewById(R.id.editTextPhone);
        loginto = findViewById(R.id.logInUsers);


        loginto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, logIn.class));
            }
        });



         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 final String email = email1.getText().toString();
                 final String pass = pass1.getText().toString();
                 final  String name = name1.getText().toString();
                 final  String phone = phone1.getText().toString();
                 if(TextUtils.isEmpty(email))
                 {
                     email1.setText("Email cannot be empty");
                     email1.requestFocus();
                 }
                 else if(TextUtils. isEmpty(pass))
                 {
                     pass1.setText("Password cannot be empty");
                     pass1.requestFocus();
                 }
                 else
                 {
                     databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot snapshot) {
                             if(snapshot.hasChild("users/email"))
                             {
                                 Toast.makeText(MainActivity.this,"The email is already registered!",Toast.LENGTH_SHORT).show();

                             }
                             else
                             {



                                 databaseReference.child("users").child(phone).child("email").setValue(email);
                                 databaseReference.child("users").child(phone).child("name").setValue(name);
                                 databaseReference.child("users").child(phone).child("password").setValue(pass);
                                 Toast.makeText(MainActivity.this, "User registered Successfully",Toast.LENGTH_SHORT).show();
                                 startActivity(new Intent(MainActivity.this, logIn.class));
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
}