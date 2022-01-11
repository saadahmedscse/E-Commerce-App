package com.caffeine.artenon.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.caffeine.artenon.Model.CourseModel;
import com.caffeine.artenon.R;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{

    ArrayList<CourseModel> list;
    String color;

    public CourseAdapter(ArrayList<CourseModel> list, String color) {
        this.list = list;
        this.color = color;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.category.setText(list.get(position).getCategory());
        holder.rating.setText(list.get(position).getReviews());
        holder.price.setText(list.get(position).getPrice() + " $");
        holder.sales.setText(list.get(position).getSales());
        holder.level.setText(list.get(position).getLevel());

        switch (color){
            case "purple":
                holder.layout.setBackgroundResource(R.drawable.bg_purple);
                break;

            case "red":
                holder.layout.setBackgroundResource(R.drawable.bg_red);
                break;

            case "blue":
                holder.layout.setBackgroundResource(R.drawable.bg_blue);
                break;

            case "green":
                holder.layout.setBackgroundResource(R.drawable.bg_green);
                break;
        }

        holder.viewDetails.setOnClickListener(view -> {
            //sldkfj
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView category, level, viewDetails, rating, price, sales;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.category);
            level = itemView.findViewById(R.id.course_level);
            viewDetails = itemView.findViewById(R.id.view_details);
            rating = itemView.findViewById(R.id.rating);
            price = itemView.findViewById(R.id.price);
            sales = itemView.findViewById(R.id.sales);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
