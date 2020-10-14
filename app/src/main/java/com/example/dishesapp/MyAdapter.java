package com.example.dishesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<String> names;
    private Context context;

    /**
     * Data passed onto the constructor
     * @param context - current instance of MainActivity
     * @param names - Dishes
     */
    public MyAdapter(Context context, List<String> names) {
        this.context = context;
        this.names = names;
    }

    // inflates the row layout
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    // binds the data to the image and the textview in each row
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.imgOfDish.setImageResource(R.drawable.ic_default_foreground);
        holder.nameOfDish.setText(this.names.get(position));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return this.names.size();
    }

    // stores and recycles views as they are scrolled off screen
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // name of dish
        public TextView nameOfDish;
        // img of dish
        private ImageView imgOfDish;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nameOfDish = itemView.findViewById(R.id.nameOfDish);
            this.imgOfDish = itemView.findViewById(R.id.imgOfDish);
        }
    }

}
