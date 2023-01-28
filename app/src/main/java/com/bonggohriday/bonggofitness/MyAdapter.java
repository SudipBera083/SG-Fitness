package com.bonggohriday.bonggofitness;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Users>list;


    public MyAdapter(Context context, ArrayList<Users> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_design,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Users users = list.get(position);
        holder.n.setText(users.getName());
        holder.d.setText(users.getDescription());
        Glide.with(holder.i.getContext())
                        .load(users.getImg_no())
//                .placeholder(R.drawable.error)
                .error(R.drawable.error)
                .into(holder.i);
//        Log.wtf("nothimg",users.getName());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {

       TextView n,d;
       ImageView i;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            n =itemView.findViewById(R.id.wk_name_1);
            d =itemView.findViewById(R.id.wk_desc_1);
            i = itemView.findViewById(R.id.wk_img_1);

        }
    }
}
