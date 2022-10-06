package com.example.scsebuddy.dynamicdesign;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scsebuddy.R;
import com.example.scsebuddy.requestsresults.CourseReview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CourseReview_RecyclerViewAdapter extends RecyclerView.Adapter<CourseReview_RecyclerViewAdapter.MyViewHolder> {
     Context context;
    ArrayList<CourseReview> reviews;

    public CourseReview_RecyclerViewAdapter(Context context, ArrayList<CourseReview> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public CourseReview_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.course_review_row, parent, false);
        return new CourseReview_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseReview_RecyclerViewAdapter.MyViewHolder holder, int position) {

//        holder.topicDescriptionTextView.setText(topics.get(position).getDescription());
//        int numberOfPosts = topics.get(position).getNumberOfPosts();
//        holder.topicNoOfPostsTextView.setText(String.valueOf(numberOfPosts));
//        holder.topicTitleTextView.setText(topics.get(position).getTitle());
        Log.e("TEST", "IM HERE?");

        holder.courseReviewTextView.setText(reviews.get(position).getDescription());
        holder.postByTextView.setText("Post By: " + reviews.get(position).getName());
        holder.datePostedTextView.setText("Date Posted: " + reviews.get(position).getDatePublished());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

//        TextView topicTitleTextView, topicDescriptionTextView, topicNoOfPostsTextView;
        TextView courseReviewTextView, postByTextView, datePostedTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            courseReviewTextView = itemView.findViewById(R.id.courseReviewTextView);
            postByTextView = itemView.findViewById(R.id.postByTextView);
            datePostedTextView = itemView.findViewById(R.id.datePostedTextView);


//            topicTitleTextView = itemView.findViewById(R.id.topicTitleTextView);
//            topicDescriptionTextView = itemView.findViewById(R.id.topicDescriptionTextView);
//            topicNoOfPostsTextView = itemView.findViewById(R.id.topicNoOfPostsTextView);
        }
    }
}