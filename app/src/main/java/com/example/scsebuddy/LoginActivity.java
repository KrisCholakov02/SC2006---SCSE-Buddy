package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.Bundle;
import android.view.Window;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

    }

    public void skipLogin (View v) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
        builder1.setMessage("Are you sure you don't want to log in?");
        builder1.setCancelable(true);


        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(v.getContext(), MapActivity.class);
                        startActivity(intent);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void forgetPW (View v) {
        Intent intent = new Intent(v.getContext(), ForgetPwActivity.class);
        startActivity(intent);
    }

    public void createAcc (View v){
        Intent intent = new Intent(v.getContext(),CreateAccountActivity.class);
        startActivity(intent);
    }

    public void loginAcc (View v){
        Intent intent = new Intent(v.getContext(),MapActivity.class);
        startActivity(intent);
    }
}