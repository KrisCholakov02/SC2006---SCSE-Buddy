package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.scsebuddy.requestsresults.ConstantVariables;
import com.example.scsebuddy.requestsresults.RetrofitInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForumPostCommentActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private int postID;
    EditText contentEditText;
    CheckBox annoymousCb;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_post_comment);
        contentEditText = this.findViewById(R.id.contentEditText);
        annoymousCb = this.findViewById(R.id.annoymousCb);
        context = this;

        Intent ii = getIntent();
        Bundle b = ii.getExtras();
        if(b!=null) {
            //Log.e("TEST",b.getString("postID") );
            postID = Integer.parseInt(b.getInt("postID")+"");
        }

    }

    public void addForumComment(View view) {
        retrofit = new Retrofit.Builder().baseUrl(ConstantVariables.getSERVER_URL()).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);


        SharedPreferences sp = getSharedPreferences("UserPreferences", Context.MODE_WORLD_READABLE);
        String email = sp.getString("USER_EMAIL", "");

        if(annoymousCb.isChecked()){
            email = "Anonymous";
        }

        Calendar calendar = Calendar.getInstance();
        Date date = (Date) calendar.getTime();
//        System.out.println("AAA" + date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("AAAA" +sdf.format(date));

        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("comments", contentEditText.getText().toString());
        map.put("dateTime", sdf.format(date));
        map.put("postID", postID+"");

        Call<Void> call = retrofitInterface.executeForumCommentPost(map);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    Toast.makeText(ForumPostCommentActivity.this, "Posted Successfully!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context, ForumViewCommentsActivity.class);
//                    courseCodeTV.setText(b.get("courseCode")+"");
//                    courseTitleTV.setText(b.get("courseTitle")+"");
//                    courseFav = Integer.parseInt(b.get("courseFav")+"");
//                    i.putExtra("courseCode", courseCodeTextView.getText()+ "");
//                    i.putExtra("courseTitle",courseTitle);
//                    i.putExtra("courseFav",courseFav);
                    startActivity(i);
                    finish();

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (response.code() == 400) {
                    Toast.makeText(ForumPostCommentActivity.this, "Wrong Credentials!", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ForumPostCommentActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}