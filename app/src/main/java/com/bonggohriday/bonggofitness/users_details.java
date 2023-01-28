package com.bonggohriday.bonggofitness;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class users_details extends AppCompatActivity {
//    ListView myListView;
//    ArrayList<String> myArrayList = new ArrayList<>();
//
//    DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bonggofitness2-default-rtdb.asia-southeast1.firebasedatabase.app");

    RecyclerView recyclerView;
    ArrayList<admin_details> list;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bonggofitness2-default-rtdb.asia-southeast1.firebasedatabase.app");
    adapterForAdmin adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_details);







        recyclerView = findViewById(R.id.r2);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter =new adapterForAdmin(this, list);
        recyclerView.setAdapter(adapter);
        databaseReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {

                    admin_details users =dataSnapshot.getValue(admin_details.class);
                    list.add(users);


                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






//        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<>(users_details.this, android.R.layout.simple_list_item_1, myArrayList);
//
//
//        myListView =(ListView) findViewById(R.id.ListView1);
//        myListView.setAdapter(myArrayAdapter);
//
//
//        mRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                mRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        for (DataSnapshot childSnapshot: snapshot.getChildren()) {
//                            String key = childSnapshot.getKey();
//
////
//                                String name = snapshot.child(key).child("name").getValue(String.class);
//                                String email = snapshot.child(key).child("email").getValue(String.class);
//                                String pass =snapshot.child(key).child("password").getValue(String.class);
//                                String ph =key;
//                                String value = "Name: "+name+"\n"+"Email: "+email+"\n"+"Password: " +pass+"\n"+"Phone: "+ph;
//                                myArrayList.add(value);
//                                myArrayAdapter.notifyDataSetChanged();
//
////
//                        }
//
//
//
//
//
//
//
//
//
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                myArrayAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


    }
}