package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.scsebuddy.dynamicdesign.Courses_RecyclerViewAdapter;
import com.example.scsebuddy.requestsresults.ConstantVariables;
import com.example.scsebuddy.requestsresults.Course;
import com.example.scsebuddy.requestsresults.CoursesResult;
import com.example.scsebuddy.requestsresults.RetrofitInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CourseActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onRestart(){
        super.onRestart();
        Intent i = new Intent(this,CourseActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        SharedPreferences sp = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);

        Spinner sortOrderSpinner = this.findViewById(R.id.sortOrderSpinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.sorting_order_spinner_content, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        sortOrderSpinner.setAdapter(adapter1);

        Spinner sortBySpinner = this.findViewById(R.id.sortBySpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.sorting_course_by_spinner_content, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        sortBySpinner.setAdapter(adapter2);

        retrofit = new Retrofit.Builder().baseUrl(ConstantVariables.getSERVER_URL()).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();
        String email = sp.getString("USER_EMAIL", null);
        map.put("email", email);

        Context context = this;

        Call<CoursesResult> getAllCourses = retrofitInterface.executeAllCourses(map);

        getAllCourses.enqueue(new Callback<CoursesResult>() {
            @Override
            public void onResponse(Call<CoursesResult> call, Response<CoursesResult> response) {
                if (response.code() == 200) {
                    CoursesResult coursesR = response.body();
                    //Log.e("TEST", coursesR.getCourses()[0].getCode().toString());
                    ArrayList<Course> courses = new ArrayList<>(Arrays.asList(coursesR.getCourses()));

                    RecyclerView coursesRecyclerView = findViewById(R.id.coursesRecycleView);

                    Courses_RecyclerViewAdapter adapter = new Courses_RecyclerViewAdapter(context, courses);
                    coursesRecyclerView.setAdapter(adapter);
                    coursesRecyclerView.setLayoutManager(new LinearLayoutManager(context));

                } else if (response.code() == 404) {
                    Toast.makeText(CourseActivity.this, "No Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CoursesResult> call, Throwable t) {
                Toast.makeText(CourseActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setUpCourseRows() {

    }

    //Bottom buttons
    public void mapScreen (View v){
        Intent intent = new Intent(v.getContext(),MapActivity.class);
        startActivity(intent);
    }

    public void courseScreen(View v){
        Intent intent = new Intent(v.getContext(),CourseActivity.class);
        startActivity(intent);
    }

    public void forumScreen (View v){
        Intent intent = new Intent(v.getContext(),ForumActivity.class);
        startActivity(intent);
    }

    public void profileScreen (View v){
        Intent intent = new Intent(v.getContext(),ProfileActivity.class);
        startActivity(intent);
    }
}