package com.caffeine.artenon.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caffeine.artenon.Adapter.CategoryAdapter;
import com.caffeine.artenon.Adapter.CourseAdapter;
import com.caffeine.artenon.Model.CourseModel;
import com.caffeine.artenon.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ImageView cart;
    private LinearLayout searchBar;

    private ImageView iconAllTopic, iconPopular, iconNewest, iconAdvanced;
    private LinearLayout allTopic, popular, newest, advanced;

    private LinearLayoutManager horizontalLayoutManager;
    private RecyclerView category;
    private ArrayList<String> Categories;

    private ViewPager2 courseItem, courseViewPager;
    private ArrayList<CourseModel> list;
    ArrayList<CourseModel> filteredList;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gettingLayoutIDs();
        courseTypeClickEvent();
        getCourses("all");

        Categories.add("Android Development");
        Categories.add("Web Development");
        Categories.add("IOS Development");
        Categories.add("Artificial Intelligence");
        Categories.add("Machine Learning");
        setCategoryItems();
    }

    private void gettingLayoutIDs(){
        cart = findViewById(R.id.cart);
        searchBar = findViewById(R.id.search_bar);

        allTopic = findViewById(R.id.all_topic);
        popular = findViewById(R.id.popular);
        newest = findViewById(R.id.newest);
        advanced = findViewById(R.id.advanced);
        iconAllTopic = findViewById(R.id.icon_all_topic);
        iconNewest = findViewById(R.id.icon_newest);
        iconPopular = findViewById(R.id.icon_popular);
        iconAdvanced = findViewById(R.id.icon_advanced);

        Categories = new ArrayList<>();
        list = new ArrayList<>();
        category = findViewById(R.id.cat_recycler_view);
        horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        category.setLayoutManager(horizontalLayoutManager);

        courseItem = findViewById(R.id.viewpager);
        courseViewPager = findViewById(R.id.course_viewpager);

        ref = FirebaseDatabase.getInstance("https://artenon-4039e-default-rtdb.firebaseio.com/").getReference().child("Artenon").child("Courses");
    }

    private void courseTypeClickEvent(){
        allTopic.setOnClickListener(view -> {
            list.clear();
            filteredList.clear();
            getCourses("all");

            allTopic.setBackgroundResource(R.drawable.bg_purple);
            iconAllTopic.setBackgroundResource(R.drawable.circle_white);
            iconAllTopic.setColorFilter(ContextCompat.getColor(this, R.color.mainColor));

            popular.setBackgroundResource(R.drawable.bg_dark_grey_25);
            iconPopular.setBackgroundResource(R.drawable.circle_red);
            iconPopular.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));

            newest.setBackgroundResource(R.drawable.bg_dark_grey_25);
            iconNewest.setBackgroundResource(R.drawable.circle_blue);
            iconNewest.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));

            advanced.setBackgroundResource(R.drawable.bg_dark_grey_25);
            iconAdvanced.setBackgroundResource(R.drawable.circle_green);
            iconAdvanced.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
        });

        popular.setOnClickListener(view -> {
            list.clear();
            filteredList.clear();
            getCourses("popular");

            allTopic.setBackgroundResource(R.drawable.bg_dark_grey_25);
            iconAllTopic.setBackgroundResource(R.drawable.circle_purple);
            iconAllTopic.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));

            popular.setBackgroundResource(R.drawable.bg_red);
            iconPopular.setBackgroundResource(R.drawable.circle_white);
            iconPopular.setColorFilter(ContextCompat.getColor(this, R.color.colorLightRed));

            newest.setBackgroundResource(R.drawable.bg_dark_grey_25);
            iconNewest.setBackgroundResource(R.drawable.circle_blue);
            iconNewest.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));

            advanced.setBackgroundResource(R.drawable.bg_dark_grey_25);
            iconAdvanced.setBackgroundResource(R.drawable.circle_green);
            iconAdvanced.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
        });

        newest.setOnClickListener(view -> {
            list.clear();
            filteredList.clear();
            getCourses("newest");

            allTopic.setBackgroundResource(R.drawable.bg_dark_grey_25);
            iconAllTopic.setBackgroundResource(R.drawable.circle_purple);
            iconAllTopic.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));

            popular.setBackgroundResource(R.drawable.bg_dark_grey_25);
            iconPopular.setBackgroundResource(R.drawable.circle_red);
            iconPopular.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));

            newest.setBackgroundResource(R.drawable.bg_blue);
            iconNewest.setBackgroundResource(R.drawable.circle_white);
            iconNewest.setColorFilter(ContextCompat.getColor(this, R.color.colorBlue));

            advanced.setBackgroundResource(R.drawable.bg_dark_grey_25);
            iconAdvanced.setBackgroundResource(R.drawable.circle_green);
            iconAdvanced.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
        });

        advanced.setOnClickListener(view -> {
            list.clear();
            filteredList.clear();
            getCourses("advanced");

            allTopic.setBackgroundResource(R.drawable.bg_dark_grey_25);
            iconAllTopic.setBackgroundResource(R.drawable.circle_purple);
            iconAllTopic.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));

            popular.setBackgroundResource(R.drawable.bg_dark_grey_25);
            iconPopular.setBackgroundResource(R.drawable.circle_red);
            iconPopular.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));

            newest.setBackgroundResource(R.drawable.bg_dark_grey_25);
            iconNewest.setBackgroundResource(R.drawable.circle_blue);
            iconNewest.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));

            advanced.setBackgroundResource(R.drawable.bg_green);
            iconAdvanced.setBackgroundResource(R.drawable.circle_white);
            iconAdvanced.setColorFilter(ContextCompat.getColor(this, R.color.colorGreen));
        });
    }

    private void setCategoryItems(){
        CategoryAdapter adapter = new CategoryAdapter(Categories, HomeActivity.this);
        category.setAdapter(adapter);
    }

    private void getCourses(String request){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    for (DataSnapshot ds2 : ds.getChildren()){
                        list.add(ds2.getValue(CourseModel.class));
                    }
                }

                CourseAdapter adapter = new CourseAdapter(list);
                filteredList = new ArrayList<>();

                if (request.equals("all")){
                    courseViewPager.setAdapter(adapter);
                }

                else if (request.equals("popular")){
                    for (CourseModel obj : list){
                        if (obj.getPopularity().equals("yes")){
                            filteredList.add(obj);
                        }
                    }
                    CourseAdapter popAdapter = new CourseAdapter(filteredList);
                    courseViewPager.setAdapter(popAdapter);
                }

                else if (request.equals("newest")){
                    for (CourseModel obj : list){
                        if (obj.getPicture().equals("new")){
                            filteredList.add(obj);
                        }
                    }
                    CourseAdapter popAdapter = new CourseAdapter(filteredList);
                    courseViewPager.setAdapter(popAdapter);
                }

                else if (request.equals("advanced")){
                    for (CourseModel obj : list){
                        if (obj.getName().startsWith("A")){
                            filteredList.add(obj);
                        }
                    }
                    CourseAdapter popAdapter = new CourseAdapter(filteredList);
                    courseViewPager.setAdapter(popAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}