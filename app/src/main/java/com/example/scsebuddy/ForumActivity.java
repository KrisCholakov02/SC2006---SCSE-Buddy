package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.scsebuddy.dynamicdesign.Topics_RecyclerViewAdapter;
import com.example.scsebuddy.requestsresults.ConstantVariables;
import com.example.scsebuddy.requestsresults.RetrofitInterface;
import com.example.scsebuddy.requestsresults.Topic;
import com.example.scsebuddy.requestsresults.TopicsResult;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForumActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        Spinner sortOrderSpinner = this.findViewById(R.id.sortOrderSpinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.sorting_order_spinner_content, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        sortOrderSpinner.setAdapter(adapter1);

        Spinner sortBySpinner = this.findViewById(R.id.sortTopicBySpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.topic_by_spinner, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        sortBySpinner.setAdapter(adapter2);

        Context context = this;
        retrofit = new Retrofit.Builder().baseUrl(ConstantVariables.getSERVER_URL()).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<TopicsResult> getAllTopics = retrofitInterface.executeAllTopics();

        getAllTopics.enqueue(new Callback<TopicsResult>() {
            @Override
            public void onResponse(Call<TopicsResult> call, Response<TopicsResult> response) {
                if (response.code() == 200) {
                    TopicsResult topicR = response.body();

                    ArrayList<Topic> topics = new ArrayList<>(Arrays.asList(topicR.getTopics()));

                    RecyclerView topicsRecyclerView = findViewById(R.id.topicsRecycleView);

                    Topics_RecyclerViewAdapter adapter = new Topics_RecyclerViewAdapter(context, topics);
                    topicsRecyclerView.setAdapter(adapter);
                    topicsRecyclerView.setLayoutManager(new LinearLayoutManager(context));

                } else if (response.code() == 404) {
                    Toast.makeText(ForumActivity.this, "No Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TopicsResult> call, Throwable t) {
                Toast.makeText(ForumActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addPost(View v){
        Intent intent = new Intent(v.getContext(),ForumPostActivity.class);
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