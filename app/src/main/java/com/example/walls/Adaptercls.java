package com.example.walls;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class Adaptercls extends RecyclerView.Adapter<Adaptercls.DataHolder> {
    private Context context;
    private ArrayList<Modelclass> model_array;
    public Adaptercls(Context context, ArrayList<Modelclass> model_array) {
        this.context = context;
        this.model_array = model_array;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.walls_screen, parent, false);
        return new DataHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        Modelclass currentItem = model_array.get(position);//current item holds position of arrayitems
        String url = currentItem.getUrl();
        int likes = currentItem.getLikes();//accessing values through currentItem
        holder.textView.setText("Likes: " + likes);

        holder.imageview.setImageURI(Uri.parse(url));
        Glide.with(context).load(url).centerInside().into(holder.imageview);//Converting context into imageview


    }

    @Override
    public int getItemCount() {
        return model_array.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder {
        public ImageView imageview;
        public TextView textView;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            imageview = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);


        }
    }
}

