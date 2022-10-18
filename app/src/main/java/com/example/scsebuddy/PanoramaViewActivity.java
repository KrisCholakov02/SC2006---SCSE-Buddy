package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

public class PanoramaViewActivity extends AppCompatActivity {

    private static VrPanoramaView panoramaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paranoma_view);
        panoramaView = findViewById(R.id.viewPanaroma);
        loadPanoramaImage();
    }

    private void loadPanoramaImage(){
        VrPanoramaView.Options options = new VrPanoramaView.Options();
        try{
            options.inputType = VrPanoramaView.Options.TYPE_MONO;
            //Picasso.get().load(R.drawable.a2).into((Target) panoramaView);
            panoramaView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.a2), options);
        } catch (Exception e){
            Log.e("TEST", e.toString());
        }
    }
}