package com.example.scsebuddy.dynamicdesign;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scsebuddy.R;
import com.example.scsebuddy.requestsresults.CourseReview;
import com.example.scsebuddy.requestsresults.ForumPost;

import java.io.Console;
import java.util.ArrayList;

public class ForumPost_RecyclerViewAdapter extends RecyclerView.Adapter<ForumPost_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<ForumPost> posts;

    public ForumPost_RecyclerViewAdapter(Context context, ArrayList<ForumPost> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ForumPost_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.form_post_row, parent, false);
        return new ForumPost_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumPost_RecyclerViewAdapter.MyViewHolder holder, int position) {
        Log.e("TEST", "IM HERE?");

        holder.topicTitleTextView.setText(posts.get(position).getTitle());
        holder.postByTextView.setText("Post By: " + posts.get(position).getName());
        holder.datePostedTextView.setText("Date Posted: " + posts.get(position).getDatePublished());
        holder.forumPostTextView.setText(posts.get(position).getDescription());
        for (int i = 0; i < posts.get(position).getTags().size(); i++) {
            if (posts.get(position).getTags().get(i).equals("")) continue;
            Button tag = new Button(context);
            tag.setText(posts.get(position).getTags().get(i));
            tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Make new window appear with delete TODO
                    Button x = (Button) view;
                    String y = x.getText().toString();
                    Log.e("TAGS",y);
                }
            });
            holder.postRowLayout.addView(tag);
        }

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView topicTitleTextView, postByTextView, datePostedTextView,forumPostTextView;
        LinearLayout postRowLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            topicTitleTextView = itemView.findViewById(R.id.topicTitleTextView);
            postByTextView = itemView.findViewById(R.id.postByTextView);
            datePostedTextView = itemView.findViewById(R.id.datePostedTextView);
            forumPostTextView = itemView.findViewById(R.id.forumPostTextView);
            postRowLayout = itemView.findViewById(R.id.postRowLayout);
        }
    }
}
