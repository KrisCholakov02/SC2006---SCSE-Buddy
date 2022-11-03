package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scsebuddy.dynamicdesign.CourseReview_RecyclerViewAdapter;
import com.example.scsebuddy.dynamicdesign.Topics_RecyclerViewAdapter;
import com.example.scsebuddy.requestsresults.ConstantVariables;
import com.example.scsebuddy.requestsresults.CourseReview;
import com.example.scsebuddy.requestsresults.CourseReviewResult;
import com.example.scsebuddy.requestsresults.RetrofitInterface;
import com.example.scsebuddy.requestsresults.Topic;
import com.example.scsebuddy.requestsresults.TopicsResult;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CourseViewActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private int courseFav;
    ImageView courseFavImageView;

    TextView courseCodeTV,courseTitleTV;
    Context context;

    Spinner sortOrderSpinner,sortBySpinner;

    //RecyclerView courseReviewRecycleView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);
        courseCodeTV = this.findViewById(R.id.courseCodeTextView);
        courseTitleTV = this.findViewById(R.id.courseTitleTextView);

        //asc/desc
        sortOrderSpinner = this.findViewById(R.id.sortOrderSpinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.sorting_order_spinner_content, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        sortOrderSpinner.setAdapter(adapter1);
        //name,year,code
        sortBySpinner = this.findViewById(R.id.sortBySpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.courseReview_spinner, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        sortBySpinner.setAdapter(adapter2);

        context = this;
        retrofit = new Retrofit.Builder().baseUrl(ConstantVariables.getSERVER_URL()).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);


        //intent passing bundle
        Intent ii = getIntent();
        Bundle b = ii.getExtras();
        courseFavImageView = this.findViewById(R.id.courseFavImageView);
        if(b!=null){
            courseCodeTV.setText(b.get("courseCode")+"");
            courseTitleTV.setText(b.get("courseTitle")+"");
            courseFav = Integer.parseInt(b.get("courseFav")+"");
            if (courseFav ==1){
                courseFavImageView.setImageResource(R.drawable.ic_course_bookmark_yellow);
            }
            else{
                courseFavImageView.setImageResource(R.drawable.ic_course_bookmark_outline);
            }
            //default load
            HashMap<String, String> map = new HashMap<>();
            String courseCode = courseCodeTV.getText().toString();
            map.put("courseCode", courseCode);
            map.put("orderBy", "desc");
            map.put("sortBy", "Date_Published");
            updateRV(map);


        }
    }

    public void updateRV(HashMap map){
        Call<CourseReviewResult> executeAllCourseReview = retrofitInterface.executeAllCourseReview(map);

        executeAllCourseReview.enqueue(new Callback<CourseReviewResult>() {
            @Override
            public void onResponse(Call<CourseReviewResult> call, Response<CourseReviewResult> response) {
                if (response.code() == 200) {
//                        TopicsResult topicR = response.body();
//
//                        ArrayList<Topic> topics = new ArrayList<>(Arrays.asList(topicR.getTopics()));
//
//                        RecyclerView topicsRecyclerView = findViewById(R.id.topicsRecycleView);
//
//                        Topics_RecyclerViewAdapter adapter = new Topics_RecyclerViewAdapter(context, topics);
//                        topicsRecyclerView.setAdapter(adapter);
//                        topicsRecyclerView.setLayoutManager(new LinearLayoutManager(context));

                    CourseReviewResult courseR = response.body();
                    ArrayList<CourseReview> reviews = new ArrayList<>(Arrays.asList(courseR.getCoursesReview()));
                    Log.e("TEST", courseR.getCoursesReview()[0].getGrade()+"");
                    RecyclerView courseReviewRecycleView = findViewById(R.id.courseReviewRecycleView);

                    CourseReview_RecyclerViewAdapter adapter = new CourseReview_RecyclerViewAdapter(context,reviews);
                    courseReviewRecycleView.setAdapter(adapter);
                    courseReviewRecycleView.setLayoutManager(new LinearLayoutManager(context));





                } else if (response.code() == 404) {
                    Toast.makeText(CourseViewActivity.this, "No Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CourseReviewResult> call, Throwable t) {
                Toast.makeText(CourseViewActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void addReview(View v){
        Intent intent = new Intent(this, CoursePostActivity.class);
        intent.putExtra("courseCode", courseCodeTV.getText());
        intent.putExtra("courseFav", courseFav);
        intent.putExtra("courseTitle", courseTitleTV.getText());
        startActivity(intent);
        finish();
    }

    public void sortByButton(View v){
        String orderBy = sortOrderSpinner.getSelectedItem().toString();
        String sortBy = sortBySpinner.getSelectedItem().toString();
        switch(sortBy){
            case "Date":
                sortBy = "Date_Published";
                break;
            case "Grade":
                sortBy = "Grade";
                break;
        }
        HashMap<String, String> map = new HashMap<>();
        String courseCode = courseCodeTV.getText().toString();
        map.put("courseCode", courseCode);
        map.put("orderBy", orderBy);
        map.put("sortBy", sortBy);
        updateRV(map);
    }


    public void addFav(View v){
        HashMap<String, String> map = new HashMap<>();
       // map.put("lName", lName);
        if(courseFav == 1) {
            //courseFavImageView.setImageResource(R.drawable.ic_course_bookmark_outline);
            courseFav = 0;
        }
        else {
            //courseFavImageView.setImageResource(R.drawable.ic_course_bookmark_yellow);
            courseFav = 1;
        }
        SharedPreferences sp = getSharedPreferences("UserPreferences", Context.MODE_WORLD_READABLE);
        String email = sp.getString("USER_EMAIL", "");

        map.put("courseFavID", courseCodeTV.getText() + "");
        map.put("courseFav", courseFav+"");
        map.put("email", email);
        Call<Void> call = retrofitInterface.executeCourseFav(map);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    Toast.makeText(CourseViewActivity.this, "Favorite Updated Successfully!", Toast.LENGTH_LONG).show();
                    if(courseFav == 1) {
                        courseFavImageView.setImageResource(R.drawable.ic_course_bookmark_yellow);
                        //courseFav = 0;
                    }
                    else {
                        courseFavImageView.setImageResource(R.drawable.ic_course_bookmark_outline);
                        //courseFav = 1;
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (response.code() == 400) {
                    Toast.makeText(CourseViewActivity.this, "Wrong Credentials!", Toast.LENGTH_LONG).show();
                    if(courseFav == 1) {
                        //courseFavImageView.setImageResource(R.drawable.ic_course_bookmark_outline);
                        courseFav = 0;
                    }
                    else {
                        //courseFavImageView.setImageResource(R.drawable.ic_course_bookmark_yellow);
                        courseFav = 1;
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CourseViewActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });




    }
}