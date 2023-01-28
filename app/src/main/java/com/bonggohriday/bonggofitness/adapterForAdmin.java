package com.bonggohriday.bonggofitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class adapterForAdmin extends RecyclerView.Adapter<adapterForAdmin.MyViewHolder> {

    Context context;
    ArrayList<admin_details> list;


    public adapterForAdmin(Context context, ArrayList<admin_details> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public adapterForAdmin.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adin_users_view,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterForAdmin.MyViewHolder holder, int position) {

        admin_details users = list.get(position);
       holder.name.setText("Name: "+users.getName());
        holder.email.setText("Email: " + users.getEmail());
        holder.given_Name.setText("Additional_Name: "+ users.getgiven_Name());
        holder.family_Name.setText("Family Name: "+ users.getFamily_Name());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {

        TextView name,email,given_Name, family_Name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.Name);
            email =itemView.findViewById(R.id.Email);
            given_Name = itemView.findViewById(R.id.GivenName);
            family_Name = itemView.findViewById(R.id.familyName);

        }
    }
}
