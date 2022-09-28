package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scsebuddy.requestsresults.ConstantVariables;
import com.example.scsebuddy.requestsresults.RetrofitInterface;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Calendar;

public class CoursePostActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    TextView courseCodeTextView ;
    EditText courseReviewEditText;
    Spinner gradeSpinner;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_post);

        gradeSpinner = this.findViewById(R.id.gradeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grade_by_spinner, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        gradeSpinner.setAdapter(adapter);

        courseCodeTextView = this.findViewById(R.id.courseCodeTextView);
        courseReviewEditText = this.findViewById(R.id.courseReviewEditText);

        retrofit = new Retrofit.Builder().baseUrl(ConstantVariables.getSERVER_URL()).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Intent ii = getIntent();
        Bundle b = ii.getExtras();
        if(b!=null) {
            courseCodeTextView.setText(b.get("courseCode") + "");
        }


    }
    public void addReviewPost (View v){
        SharedPreferences sp = getSharedPreferences("UserPreferences", Context.MODE_WORLD_READABLE);
        String email = sp.getString("USER_EMAIL", "");


        Calendar calendar = Calendar.getInstance();
        Date date = (Date) calendar.getTime();
//        System.out.println("AAA" + date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("AAAA" +sdf.format(date));

        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("courseCode", courseCodeTextView.getText()+"");
        map.put("grade", gradeSpinner.getSelectedItem().toString());
        map.put("dateTime", sdf.format(date));
        map.put("content", courseCodeTextView.getText()+"");

        Call<Void> call = retrofitInterface.executeCoursePost(map);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    Toast.makeText(CoursePostActivity.this, "Posted Successfully!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context, CourseViewActivity.class);
                    startActivity(i);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (response.code() == 400) {
                    Toast.makeText(CoursePostActivity.this, "Wrong Credentials!", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CoursePostActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
