package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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