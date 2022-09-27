package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class CourseViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);
        Intent ii = getIntent();
        Bundle b = ii.getExtras();

        if(b!=null){
            System.out.println("PLEASE " + b.get("TEST"));
        }
    }
}