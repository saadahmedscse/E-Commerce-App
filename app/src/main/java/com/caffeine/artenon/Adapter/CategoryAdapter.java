package com.caffeine.artenon.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.caffeine.artenon.Model.CourseModel;
import com.caffeine.artenon.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    ArrayList<String> list;
    Activity activity;
    ViewPager2 viewpager;
    View placeholder;
    private int selected_position = -1, count = 0, count2 = 0;
    ArrayList<CourseModel> courseList = new ArrayList<>();

    public CategoryAdapter(ArrayList<String> list, Activity activity, ViewPager2 viewpager, View placeholder) {
        this.list = list;
        this.activity = activity;
        this.viewpager = viewpager;
        this.placeholder = placeholder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.category.setText(list.get(position));

        switch (holder.category.getText().toString()){
            case "Android Development":
                holder.icon.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.android));
                break;

            case "Web Development":
                holder.icon.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.worldwide));
                break;

            case "IOS Development":
                holder.icon.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.apple));
                break;

            case "Artificial Intelligence":
                holder.icon.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ai));
                break;

            case "Machine Learning":
                holder.icon.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ml));
                break;
        }

        if (selected_position == position){
            holder.item.setBackgroundResource(R.drawable.bg_purple);
            holder.icon.setBackgroundResource(R.drawable.circle_white);
            holder.icon.setColorFilter(ContextCompat.getColor(activity, R.color.mainColor));
        }

        else {
            holder.item.setBackgroundResource(R.drawable.bg_dark_grey_25);
            holder.icon.setBackgroundResource(R.drawable.circle_purple);
            holder.icon.setColorFilter(ContextCompat.getColor(activity, R.color.colorWhite));
        }

        if (position == 0 && count == 0){
            holder.item.setBackgroundResource(R.drawable.bg_purple);
            holder.icon.setBackgroundResource(R.drawable.circle_white);
            holder.icon.setColorFilter(ContextCompat.getColor(activity, R.color.mainColor));
        }

        if (count2 == 0){
            getItems("Web Development");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getItems(String cat){
        DatabaseReference ref = FirebaseDatabase.getInstance("https://artenon-4039e-default-rtdb.firebaseio.com/")
                .getReference().child("Artenon").child("Courses").child(cat);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                courseList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    courseList.add(ds.getValue(CourseModel.class));
                }

                CourseAdapter adapter = new CourseAdapter(courseList, "purple");
                if (adapter.getItemCount() > 0) {
                    placeholder.setVisibility(View.GONE);
                } else {
                    placeholder.setVisibility(View.VISIBLE);
                }
                viewpager.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout item;
        TextView category;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.category_item);
            category = itemView.findViewById(R.id.category);
            icon = itemView.findViewById(R.id.icon);

            item.setOnClickListener(view -> {
                count++;
                count2 = 1;
                int position = getAdapterPosition();
                selected_position = position;
                notifyDataSetChanged();

                switch (category.getText().toString()){
                    case "Android Development":
                        getItems("Android Development");
                        break;

                    case "Web Development":
                        getItems("Web Development");
                        break;

                    case "IOS Development":
                        getItems("IOS Development");
                        break;

                    case "Artificial Intelligence":
                        getItems("Artificial Intelligence");
                        break;

                    case "Machine Learning":
                        getItems("Machine Learning");
                        break;
                }
            });
        }
    }
}
