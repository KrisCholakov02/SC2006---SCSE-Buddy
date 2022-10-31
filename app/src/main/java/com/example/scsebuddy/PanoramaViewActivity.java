package com.example.scsebuddy;


import androidx.appcompat.app.AppCompatActivity;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.scsebuddy.requestsresults.ConstantVariables;

import java.util.Arrays;

public class PanoramaViewActivity extends AppCompatActivity {

    private static VrPanoramaView panoramaView;
    private String[] path;
    private int pageNum = 0;
    TextView pageNumTextView;
    //SharedPreferences sp = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paranoma_view);
        panoramaView = findViewById(R.id.viewPanaroma);
        pageNumTextView = findViewById(R.id.pageNumTextView);
        Intent ii = getIntent();
        Bundle b = ii.getExtras();
        if(b!=null){
            path = (String[]) b.get("path");
            if(path != null)
                loadPanoramaImage(path[pageNum]);
            else
                loadPanoramaImage("a1");
        }
        //Log.e("TEST", path.length + "");


        //loadPanoramaImage("");
    }

    private void loadPanoramaImage(String photoID) {
        pageNumTextView.setText(pageNum + "");
        VrPanoramaView.Options options = new VrPanoramaView.Options();
        try {
            options.inputType = VrPanoramaView.Options.TYPE_MONO;
            //Picasso.get().load(R.drawable.a2).into((Target) panoramaView);
            //panoramaView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.testzoom), options);
            panoramaView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), chooseDrawable(photoID)), options);
        } catch (Exception e) {
            Log.e("TEST", e.toString());
        }
    }

    public void firstMap(View v){
        if(path != null){
            pageNum = 0;
            loadPanoramaImage(path[pageNum]);
        }
        else
            loadPanoramaImage("a1");
    }

    public void backMap(View v){
        if(path != null){
            if(pageNum != 0){
                pageNum--;
            }
            loadPanoramaImage(path[pageNum]);
        }
        else
            loadPanoramaImage("a1");
    }

    public void nextMap(View v){
        if(path != null){
            if(pageNum != path.length-1){
                pageNum++;
            }
            loadPanoramaImage(path[pageNum]);
        }
        else
            loadPanoramaImage("a1");
    }

    public void lastMap(View v){

        if(path != null){
            pageNum = path.length-1;
            loadPanoramaImage(path[pageNum]);
        }
        else
            loadPanoramaImage("a1");
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