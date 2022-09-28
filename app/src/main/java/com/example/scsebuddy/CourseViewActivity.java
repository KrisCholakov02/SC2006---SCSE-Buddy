package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CourseViewActivity extends AppCompatActivity {

    TextView courseCodeTV,courseTitleTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);
        courseCodeTV = this.findViewById(R.id.courseCodeTextView);
        courseTitleTV = this.findViewById(R.id.courseTitleTextView);

        //intent passing bundle
        Intent ii = getIntent();
        Bundle b = ii.getExtras();
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
    public void addReview(View v){
        Intent intent = new Intent(this, CoursePostActivity.class);
        intent.putExtra("courseCode", courseCodeTV.getText());
        startActivity(intent);

    }
}