package com.example.scsebuddy.dynamicdesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scsebuddy.R;
import com.example.scsebuddy.requestsresults.Course;

import java.util.ArrayList;

public class Course_RecyclerViewAdapter extends RecyclerView.Adapter<Course_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<Course> courses;

    public Course_RecyclerViewAdapter (Context context, ArrayList<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public Course_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.course_row, parent, false);
        return new Course_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Course_RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.ausTextView.setText(courses.get(position).getAUs());
        holder.courseCodeTextView.setText(courses.get(position).getCode());
        holder.courseTitleTextView.setText(courses.get(position).getTitle());
        int fav = courses.get(position).getFavorite();
        if (fav == 0) {
            holder.favouriteImageView.setImageResource(R.drawable.ic_course_bookmark_outline);
        } else {
            holder.favouriteImageView.setImageResource(R.drawable.ic_course_bookmark_yellow);
        }
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView courseCodeTextView, courseTitleTextView, ausTextView;
        ImageView favouriteImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            favouriteImageView = itemView.findViewById(R.id.favouriteImageView);
            courseCodeTextView = itemView.findViewById(R.id.courseCodeTextView);
            courseTitleTextView = itemView.findViewById(R.id.courseTitleTextView);
            ausTextView = itemView.findViewById(R.id.ausTextView);
        }
    }
}
