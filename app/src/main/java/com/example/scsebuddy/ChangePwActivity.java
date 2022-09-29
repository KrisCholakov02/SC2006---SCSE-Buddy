package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scsebuddy.requestsresults.ConstantVariables;
import com.example.scsebuddy.requestsresults.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangePwActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pw);

        retrofit = new Retrofit.Builder().baseUrl(ConstantVariables.getSERVER_URL()).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

    }

    public void changePW(View v) {
        System.out.println("I HAVE ARRIVED");
        EditText txtOldPW = findViewById(R.id.txtOldPW);
        EditText txtNewPw = findViewById(R.id.txtNewPw);
        EditText txtReNewPw = findViewById(R.id.txtReNewPw);

        SharedPreferences sp = getSharedPreferences("UserPreferences", Context.MODE_WORLD_READABLE);
        String email = sp.getString("USER_EMAIL", "");

        if (txtNewPw.getText().toString().trim().equals(txtReNewPw.getText().toString().trim())) {
            System.out.println("HERE?");
            HashMap<String, String> map = new HashMap<>();
            map.put("oldPW", txtOldPW.getText().toString().trim());
            map.put("newPW", txtNewPw.getText().toString().trim());
            map.put("email", email);

            Call<Void> call = retrofitInterface.executeChangePW(map);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 200) {
                        Toast.makeText(ChangePwActivity.this, "Change successfully!", Toast.LENGTH_LONG).show();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(v.getContext(), LoginActivity.class);
                        startActivity(intent);

                    } else if (response.code() == 404) {
                        Toast.makeText(ChangePwActivity.this, "Wrong Password!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(ChangePwActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        } else {
            // toast smt for reenter pw
            Toast.makeText(ChangePwActivity.this, "New Password Not Same!", Toast.LENGTH_LONG).show();
        }
    }
}