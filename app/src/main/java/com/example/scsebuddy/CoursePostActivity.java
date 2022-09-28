package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CoursePostActivity extends AppCompatActivity {

    TextView courseCodeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_post);

        courseCodeTextView = this.findViewById(R.id.courseCodeTextView);

        Intent ii = getIntent();
        Bundle b = ii.getExtras();
        ImageView courseFavImageView = this.findViewById(R.id.courseFavImageView);
        if(b!=null) {
            courseCodeTextView.setText(b.get("courseCode") + "");
        }
    }
}