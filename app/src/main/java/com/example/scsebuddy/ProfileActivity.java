package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sp = getSharedPreferences("UserPreferences", Context.MODE_WORLD_READABLE);

        TextView fullNameField = this.findViewById(R.id.fullNameTextViews);
        TextView emailField = this.findViewById(R.id.emailTextView);

        String fName = sp.getString("USER_F_NAME", "");
        String lName = sp.getString("USER_L_NAME", "");
        String email = sp.getString("USER_EMAIL", "");

        fullNameField.setText(fName + " " + lName);
        emailField.setText(email);
    }

    public void changePW (View v){
        Intent intent = new Intent(v.getContext(),ChangePwActivity.class);
        startActivity(intent);
    }

    public void updateDetails (View v){
        Intent intent = new Intent(v.getContext(),ChangeAccountDetailsActivity.class);
        startActivity(intent);
    }
    public void openFAQs (View v){
        Intent intent = new Intent(v.getContext(), FAQActivity.class);
        startActivity(intent);
    }

    public void logoutTextView (View v){
        SharedPreferences sh = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        editor.putString("USER_F_NAME", null);
        editor.putString("USER_L_NAME", null);
        editor.putString("USER_EMAIL", null);
        editor.commit();

        Intent intent = new Intent(v.getContext(),LoginActivity.class);
        startActivity(intent);
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