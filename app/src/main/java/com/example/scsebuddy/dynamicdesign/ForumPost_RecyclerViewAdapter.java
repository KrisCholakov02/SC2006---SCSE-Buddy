package com.example.scsebuddy.dynamicdesign;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scsebuddy.CourseViewActivity;
import com.example.scsebuddy.ForumPostActivity;
import com.example.scsebuddy.ForumViewCommentsActivity;
import com.example.scsebuddy.MapActivity;
import com.example.scsebuddy.R;
import com.example.scsebuddy.requestsresults.CourseReview;
import com.example.scsebuddy.requestsresults.ForumComment;
import com.example.scsebuddy.requestsresults.ForumPost;

import java.io.Console;
import java.util.ArrayList;

public class ForumPost_RecyclerViewAdapter extends RecyclerView.Adapter<ForumPost_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<ForumPost> posts;
    static Context context1;
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
                    Intent intent = new Intent(view.getContext(), MapActivity.class);
                    Button tag = (Button) view;
                    String tagLocation = tag.getText().toString();
                    intent.putExtra("mapDestination", tagLocation);

                    context.startActivity(intent);
                }
            });
            holder.postRowLayout.addView(tag);
        }
        if(posts.get(position).getFavorite() == 1){
            holder.btnForumLike.setImageResource(R.drawable.thumb_up_24_red);
        }
        else {
            holder.btnForumLike.setImageResource(R.drawable.thumb_up_24);
        }
        holder.iDTextView.setText(posts.get(position).getID()+"");
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView topicTitleTextView, postByTextView, datePostedTextView,forumPostTextView, iDTextView;
        LinearLayout postRowLayout;
        ImageView btnForumLike;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            context1 = itemView.getContext();
            topicTitleTextView = itemView.findViewById(R.id.topicTitleTextView);
            postByTextView = itemView.findViewById(R.id.postByTextView);
            datePostedTextView = itemView.findViewById(R.id.datePostedTextView);
            forumPostTextView = itemView.findViewById(R.id.forumPostTextView);
            postRowLayout = itemView.findViewById(R.id.postRowLayout);
            btnForumLike = itemView.findViewById(R.id.btnForumLike);
            iDTextView = itemView.findViewById(R.id.iDTextView);
        }

        @Override
        public void onClick(View view) {
            final Intent intent;
//            intent = new Intent(context1, CourseViewActivity.class);
//            intent.putExtra("courseCode", courseCodeTextView.getText());
//            intent.putExtra("courseTitle", courseTitleTextView.getText());
//            intent.putExtra("courseFav", courseFavTextView.getText());
//            context1.startActivity(intent);
            Log.e("TEST", "HERE?");
            intent = new Intent(context1, ForumViewCommentsActivity.class);
            intent.putExtra("postID", iDTextView.getText()+"");
            intent.putExtra("forumPost", forumPostTextView.getText()+"");
            intent.putExtra("datePosted", datePostedTextView.getText()+"");
            intent.putExtra("postBy", postByTextView.getText()+"");
            intent.putExtra("topicTitle",topicTitleTextView.getText()+"");
            context1.startActivity(intent);
        }
    }
}
