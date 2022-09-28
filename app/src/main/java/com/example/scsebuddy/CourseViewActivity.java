package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CourseViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);
        Intent ii = getIntent();
        Bundle b = ii.getExtras();
        TextView courseCodeTV = this.findViewById(R.id.courseCodeTextView);
        TextView courseTitleTV = this.findViewById(R.id.courseTitleTextView);
        ImageView courseFavImageView = this.findViewById(R.id.courseFavImageView);
        if(b!=null){
            courseCodeTV.setText(b.get("courseCode")+"");
            courseTitleTV.setText(b.get("courseTitle")+"");
            int courseFav = Integer.parseInt(b.get("courseFav")+"");
            if (courseFav ==1){
                courseFavImageView.setImageResource(R.drawable.ic_course_bookmark_yellow);
            }
            else{
                courseFavImageView.setImageResource(R.drawable.ic_course_bookmark_outline);
            }
        }
    }
}