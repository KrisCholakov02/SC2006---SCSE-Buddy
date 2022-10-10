package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
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

public class ForumPostActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    Spinner topicSpinner;
    CheckBox annoymousCb;
    EditText contentEditText, titleEditText;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_post);

        topicSpinner = this.findViewById(R.id.topicSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.topic_by_spinner, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        topicSpinner.setAdapter(adapter);

        retrofit = new Retrofit.Builder().baseUrl(ConstantVariables.getSERVER_URL()).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        contentEditText = this.findViewById(R.id.contentEditText);
        titleEditText = this.findViewById(R.id.titleEditText);
        annoymousCb = this.findViewById(R.id.annoymousCb);

        Intent ii = getIntent();
        Bundle b = ii.getExtras();
        if(b!=null){
            selectValue(topicSpinner,b.get("forumTopic")+"");
            //Log.e("HELLO", b.get("topicTitle")+"");
        }

    }

    public void addForumPost(View v){

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
        map.put("title", titleEditText.getText()+"");
        map.put("topic", topicSpinner.getSelectedItem().toString());
        map.put("dateTime", sdf.format(date));
        map.put("content", contentEditText.getText()+"");

        Call<Void> call = retrofitInterface.executeForumPost(map);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    Toast.makeText(ForumPostActivity.this, "Posted Successfully!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context, ForumViewActivity.class);
                    startActivity(i);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (response.code() == 400) {
                    Toast.makeText(ForumPostActivity.this, "Wrong Credentials!", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ForumPostActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void selectValue(Spinner spinner, Object value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }
}