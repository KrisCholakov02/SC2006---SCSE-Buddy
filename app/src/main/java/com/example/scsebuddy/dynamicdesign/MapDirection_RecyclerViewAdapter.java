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
import com.example.scsebuddy.PanoramaViewActivity;
import com.example.scsebuddy.R;
import com.example.scsebuddy.requestsresults.Course;
import com.example.scsebuddy.requestsresults.Direction;

import java.util.ArrayList;

public class MapDirection_RecyclerViewAdapter extends RecyclerView.Adapter<MapDirection_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    static Context context1;
    ArrayList<Direction> direction;
    static String[] path = new String[1];

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
        //path = new String[direction.size()];
        //path[position] = direction.get(position).getDirectionMessage();
        holder.directionArrowImageView.setImageResource(imageSrc);
        holder.photoIDTextView.setText(direction.get(position).getNextLocation());
        holder.photo360ImageView.setImageResource(chooseDrawable(direction.get(position).getNextLocation()));
    }

    @Override
    public int getItemCount() {
        return direction.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView directionMessageTextView, photoIDTextView;
        ImageView photo360ImageView;
        ImageView directionArrowImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            context1 = itemView.getContext();
            directionMessageTextView = itemView.findViewById(R.id.directionMessageTextView);
            photo360ImageView = itemView.findViewById(R.id.photo360ImageView);
            directionArrowImageView = itemView.findViewById(R.id.directionArrowImageView);
            photoIDTextView = itemView.findViewById(R.id.photoIDTextView);
        }

        @Override
        public void onClick(View view) {
//            final Intent intent;
//            intent = new Intent(context1,CourseViewActivity.class);
//            intent.putExtra("courseCode", courseCodeTextView.getText());
//            intent.putExtra("courseTitle", courseTitleTextView.getText());
//            intent.putExtra("courseFav", courseFavTextView.getText());
//            context1.startActivity(intent);

            final  Intent intent;
            path[0] = photoIDTextView.getText().toString();
            intent = new Intent(context1, PanoramaViewActivity.class);
            intent.putExtra("path", path);
            context1.startActivity(intent);

        }
    }

        private int chooseDrawable(String drawableName) {
            switch (drawableName) {
                case "a1":
                    return R.drawable.a1;
                //break;
                case "a2":
                    return R.drawable.a2;
                //break;
                case "a3":
                    return R.drawable.a3;
                //break;
                case "a4":
                    return R.drawable.a4;
                //break;
                case "a5":
                    return R.drawable.a5;
                //break;
                case "a6":
                    return R.drawable.a6;
                //break;
                case "a7":
                    return R.drawable.a7;
                //break;
                case "a8":
                    return R.drawable.a8;
                //break;
                case "a9":
                    return R.drawable.a9;
                //break;
                case "a10":
                    return R.drawable.a10;
                //break;
                case "a11":
                    return R.drawable.a11;
                //break;
                case "a12":
                    return R.drawable.a12;
                //break;
                case "a13":
                    return R.drawable.a13;
                //break;
                case "a14":
                    return R.drawable.a14;
                //break;
                case "a15":
                    return R.drawable.a15;
                //break;
                case "a16":
                    return R.drawable.a16;
                //break;
                case "a17":
                    return R.drawable.a17;
                //break;
                case "a18":
                    return R.drawable.a18;
                //break;
                case "a19":
                    return R.drawable.a19;
                //break;
                case "a20":
                    return R.drawable.a20;
                //break;
                case "a21":
                    return R.drawable.a21;
                //break;
                case "a22":
                    return R.drawable.a22;
                //break;
                case "a23":
                    return R.drawable.a23;
                //break;
                case "a24":
                    return R.drawable.a24;
                //break;
                case "a25":
                    return R.drawable.a25;
                //break;
                case "a26":
                    return R.drawable.a26;
                //break;
                case "a27":
                    return R.drawable.a27;
                //break;
                case "a28":
                    return R.drawable.a28;
                //break;
                case "a29":
                    return R.drawable.a29;
                //break;
                case "a30":
                    return R.drawable.a30;
                //break;
                case "a31":
                    return R.drawable.a31;
                //break;
                case "a32":
                    return R.drawable.a32;
                //break;
                case "a33":
                    return R.drawable.a33;
                //break;
                case "a34":
                    return R.drawable.a34;
                //break;
                case "a35":
                    return R.drawable.a35;
                //break;
                case "a36":
                    return R.drawable.a36;
                //break;
                case "a37":
                    return R.drawable.a37;
                //break;
                case "a38":
                    return R.drawable.a38;
                //break;
                case "a39":
                    return R.drawable.a39;
                //break;
                case "a40":
                    return R.drawable.a40;
                //break;
                case "a41":
                    return R.drawable.a41;
                //break;
                case "a42":
                    return R.drawable.a42;
                //break;
                case "a43":
                    return R.drawable.a43;
                //break;
                default:
                    break;
            }
            return -1;
        }

}
