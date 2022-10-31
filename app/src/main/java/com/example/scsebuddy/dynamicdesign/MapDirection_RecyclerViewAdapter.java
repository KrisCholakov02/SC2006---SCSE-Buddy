package com.example.scsebuddy.dynamicdesign;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scsebuddy.CourseViewActivity;
import com.example.scsebuddy.LoginActivity;
import com.example.scsebuddy.MapActivity;
import com.example.scsebuddy.R;
import com.example.scsebuddy.requestsresults.Course;
import com.example.scsebuddy.requestsresults.Direction;

import java.util.ArrayList;

public class MapDirection_RecyclerViewAdapter extends RecyclerView.Adapter<MapDirection_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    static Context context1;
    ArrayList<Direction> direction;

    public MapDirection_RecyclerViewAdapter(Context context, ArrayList<Direction> direction) {
        this.context = context;
        this.direction = direction;
    }

    @NonNull
    @Override
    public MapDirection_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.direction_row, parent, false); //Add new card VIEW!! TODO
        return new MapDirection_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MapDirection_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.directionMessageTextView.setText(direction.get(position).getDirectionMessage());

        int imageSrc = R.drawable.logo;
        switch (direction.get(position).getPhotoSrc()) {
            case "horizontal_arrow":
                imageSrc = R.drawable.horizontal_arrow;
                break;
            case "up_arrow":
                imageSrc = R.drawable.up_arrow;
                break;
            case "down_arrow":
                imageSrc = R.drawable.down_arrow;
                break;
        }
        holder.directionArrowImageView.setImageResource(imageSrc);
    }

    @Override
    public int getItemCount() {
        return direction.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView directionMessageTextView;
        ImageView photo360ImageView;
        ImageView directionArrowImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            context1 = itemView.getContext();
            directionMessageTextView = itemView.findViewById(R.id.directionMessageTextView);
            photo360ImageView = itemView.findViewById(R.id.photo360ImageView);
            directionArrowImageView = itemView.findViewById(R.id.directionArrowImageView);
        }

        @Override
        public void onClick(View view) {
//            final Intent intent;
//            intent = new Intent(context1,CourseViewActivity.class);
//            intent.putExtra("courseCode", courseCodeTextView.getText());
//            intent.putExtra("courseTitle", courseTitleTextView.getText());
//            intent.putExtra("courseFav", courseFavTextView.getText());
//            context1.startActivity(intent);
        }
    }
}
