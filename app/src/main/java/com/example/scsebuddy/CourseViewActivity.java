package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scsebuddy.requestsresults.ConstantVariables;
import com.example.scsebuddy.requestsresults.RetrofitInterface;

import org.w3c.dom.Text;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);
        courseCodeTV = this.findViewById(R.id.courseCodeTextView);
        courseTitleTV = this.findViewById(R.id.courseTitleTextView);


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
        }
    }
    public void addReview(View v){
        Intent intent = new Intent(this, CoursePostActivity.class);
        intent.putExtra("courseCode", courseCodeTV.getText());
        startActivity(intent);

    }


    public void addFav(View v){
        HashMap<String, String> map = new HashMap<>();
       // map.put("lName", lName);
        if(courseFav == 1) {
            courseFavImageView.setImageResource(R.drawable.ic_course_bookmark_outline);
            courseFav = 0;
        }
        else {
            courseFavImageView.setImageResource(R.drawable.ic_course_bookmark_yellow);
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
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (response.code() == 400) {
                    Toast.makeText(CourseViewActivity.this, "Wrong Credentials!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CourseViewActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });




    }
}