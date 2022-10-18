package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SharedPreferences sp = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);

        String email = sp.getString("USER_EMAIL", null);

        if (email == null) {
            this.findViewById(R.id.btnForum).setVisibility(View.GONE);
            this.findViewById(R.id.btnCourse).setVisibility(View.GONE);
            this.findViewById(R.id.btnMap).setVisibility(View.GONE);
            this.findViewById(R.id.btnProfile).setVisibility(View.GONE);

            LinearLayout mainPagesButtons = this.findViewById(R.id.mainPagesButtonsLayout);
            mainPagesButtons.setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.5f);

            TextView noAccountTextView = new TextView(this);
            mainPagesButtons.addView(noAccountTextView);
            noAccountTextView.setText("You have not logged in you account!");
            noAccountTextView.setLayoutParams(param1);
            noAccountTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            noAccountTextView.setGravity(Gravity.CENTER);
            noAccountTextView.setTextColor(noAccountTextView.getContext().getColor(R.color.grey_text));

            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 2.5f);

            Button loginButton = new Button(this);
            mainPagesButtons.addView(loginButton);
            loginButton.setText("Log In");
            loginButton.getResources().getFont(R.font.poppins_medium);
            loginButton.setTextColor(loginButton.getContext().getColor(R.color.grey_text));
            loginButton.setLayoutParams(params2);
            loginButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            loginButton.setGravity(Gravity.CENTER);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    //Bottom buttons
    public void mapScreen (View v){
        Intent intent = new Intent(v.getContext(), PanoramaView.class);
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