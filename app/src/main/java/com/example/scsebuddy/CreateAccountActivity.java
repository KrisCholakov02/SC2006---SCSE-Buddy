package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scsebuddy.requestsresults.LoginResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.scsebuddy.requestsresults.RetrofitInterface;

public class CreateAccountActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.0.102:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        this.findViewById(R.id.createAccountButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
                EditText emailInput = findViewById(R.id.emailEditText);
                EditText passwordInput = findViewById(R.id.passwordEditText);
                EditText fNameInput = findViewById(R.id.firstNameEditText);
                EditText lNameInput = findViewById(R.id.lastNameEditText);
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                String fName = fNameInput.getText().toString();
                String lName = lNameInput.getText().toString();
                map.put("email", email);
                map.put("password", password);
                map.put("fName", fName);
                map.put("lName", lName);

                Call<Void> call = retrofitInterface.executeSignup(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            Toast.makeText(CreateAccountActivity.this, "Signed up successfully!", Toast.LENGTH_LONG).show();
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(v.getContext(), LoginActivity.class);
                            startActivity(intent);
                        } else if (response.code() == 400) {
                            Toast.makeText(CreateAccountActivity.this, "Wrong Credentials!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(CreateAccountActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    public void loginTextView (View v){
        Intent intent = new Intent(v.getContext(),LoginActivity.class);
        startActivity(intent);
    }
}