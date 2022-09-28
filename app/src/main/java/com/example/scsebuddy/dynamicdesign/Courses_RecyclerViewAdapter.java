package com.example.scsebuddy.dynamicdesign;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scsebuddy.CourseViewActivity;
import com.example.scsebuddy.R;
import com.example.scsebuddy.requestsresults.Course;

import java.util.ArrayList;

public class Courses_RecyclerViewAdapter extends RecyclerView.Adapter<Courses_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    static Context context1;
    ArrayList<Course> courses;
    static int favv = 0;

    public Courses_RecyclerViewAdapter(Context context, ArrayList<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public Courses_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.course_row, parent, false);
        return new Courses_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Courses_RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.ausTextView.setText(courses.get(position).getAUs() + " AUs");
        holder.courseCodeTextView.setText(courses.get(position).getCode());
        holder.courseTitleTextView.setText(courses.get(position).getTitle());
        int fav = courses.get(position).getFavorite();
        if (fav == 0) {
            holder.favouriteImageView.setImageResource(R.drawable.ic_course_bookmark_outline);
            holder.courseFavTextView.setText(fav +"");
        } else {
            holder.favouriteImageView.setImageResource(R.drawable.ic_course_bookmark_yellow);
            holder.courseFavTextView.setText(fav+ "");
        }
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView courseCodeTextView, courseTitleTextView, ausTextView, courseFavTextView;
        ImageView favouriteImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            context1 = itemView.getContext();

            favouriteImageView = itemView.findViewById(R.id.favouriteImageView);
            courseCodeTextView = itemView.findViewById(R.id.courseCodeTextView);
            courseTitleTextView = itemView.findViewById(R.id.courseTitleTextView);
            courseFavTextView = itemView.findViewById(R.id.courseFavTextView);
            ausTextView = itemView.findViewById(R.id.ausTextView);
        }

        @Override
        public void onClick(View view) {
            final Intent intent;
            intent = new Intent(context1,CourseViewActivity.class);
            intent.putExtra("courseCode", courseCodeTextView.getText());
            intent.putExtra("courseTitle", courseTitleTextView.getText());
            intent.putExtra("courseFav", courseFavTextView.getText());
            context1.startActivity(intent);
        }
    }
}
