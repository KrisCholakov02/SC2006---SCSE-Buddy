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

import com.example.scsebuddy.ForumPostActivity;
import com.example.scsebuddy.MapActivity;
import com.example.scsebuddy.R;
import com.example.scsebuddy.requestsresults.CourseReview;
import com.example.scsebuddy.requestsresults.ForumComment;
import com.example.scsebuddy.requestsresults.ForumPost;

import java.io.Console;
import java.util.ArrayList;

public class ForumComment_RecyclerViewAdapter extends RecyclerView.Adapter<ForumComment_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<ForumComment> forumComments;

    public ForumComment_RecyclerViewAdapter(Context context, ArrayList<ForumComment> forumComments) {
        this.context = context;
        this.forumComments = forumComments;
    }

    @NonNull
    @Override
    public ForumComment_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.form_post_row, parent, false);
        return new ForumComment_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumComment_RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.postByTextView.setText("Post By: " + forumComments.get(position).getName());
        holder.forumPostTextView.setText(forumComments.get(position).getDescription());
        holder.datePostedTextView.setText("Date Posted: " + forumComments.get(position).getDatePublished());
//        Log.e("TEST", "IM HERE?");
//
//        holder.topicTitleTextView.setText(posts.get(position).getTitle());
//        holder.postByTextView.setText("Post By: " + posts.get(position).getName());
//        holder.datePostedTextView.setText("Date Posted: " + posts.get(position).getDatePublished());
//        holder.forumPostTextView.setText(posts.get(position).getDescription());
//        for (int i = 0; i < posts.get(position).getTags().size(); i++) {
//            if (posts.get(position).getTags().get(i).equals("")) continue;
//            Button tag = new Button(context);
//            tag.setText(posts.get(position).getTags().get(i));
//            tag.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(view.getContext(), MapActivity.class);
//                    Button tag = (Button) view;
//                    String tagLocation = tag.getText().toString();
//                    intent.putExtra("mapDestination", tagLocation);
//
//                    context.startActivity(intent);
//                }
//            });
//            holder.postRowLayout.addView(tag);
//        }
//        if(posts.get(position).getFavorite() == 1){
//            holder.btnForumLike.setImageResource(R.drawable.thumb_up_24_red);
//        }
//        else {
//            holder.btnForumLike.setImageResource(R.drawable.thumb_up_24);
//        }
    }

    @Override
    public int getItemCount() {
        return forumComments.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView topicTitleTextView, postByTextView, datePostedTextView,forumPostTextView;
        LinearLayout postRowLayout;
        ImageView btnForumLike;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            topicTitleTextView = itemView.findViewById(R.id.topicTitleTextView);
            postByTextView = itemView.findViewById(R.id.postByTextView);
            datePostedTextView = itemView.findViewById(R.id.datePostedTextView);
            forumPostTextView = itemView.findViewById(R.id.forumPostTextView);
            postRowLayout = itemView.findViewById(R.id.postRowLayout);
            btnForumLike = itemView.findViewById(R.id.btnForumLike);
            btnForumLike.setVisibility(View.GONE);
            topicTitleTextView.setVisibility(View.GONE);
            postRowLayout.setVisibility(View.GONE);


        }
    }
}
