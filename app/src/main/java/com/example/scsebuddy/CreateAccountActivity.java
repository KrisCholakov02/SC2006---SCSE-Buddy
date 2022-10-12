package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scsebuddy.requestsresults.ConstantVariables;
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
    String valid_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        retrofit = new Retrofit.Builder().baseUrl(ConstantVariables.getSERVER_URL()).addConverterFactory(GsonConverterFactory.create()).build();

        retrofitInterface = retrofit.create(RetrofitInterface.class); //

        EditText emailInput = findViewById(R.id.emailEditText);
        EditText passwordInput = findViewById(R.id.passwordEditText);
        EditText fNameInput = findViewById(R.id.firstNameEditText);
        EditText lNameInput = findViewById(R.id.lastNameEditText);


        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                is_Valid_Email(emailInput);
            }
            public void is_Valid_Email(EditText edt) {
                if (edt.getText().toString() == null) {
                    edt.setError("Invalid Email Address");
                    valid_email = null;
                } else if (isEmailValid(edt.getText().toString()) == false) {
                    edt.setError("Invalid Email Address");
                    valid_email = null;
                } else {
                    valid_email = edt.getText().toString();
                }
            }

            boolean isEmailValid(CharSequence email) {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches();
            } // end of TextWatcher (email)
        });

        this.findViewById(R.id.createAccountButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valid_email!= null){
                    HashMap<String, String> map = new HashMap<>();
                    String email = emailInput.getText().toString().trim();
                    String password = passwordInput.getText().toString().trim();
                    String fName = fNameInput.getText().toString().trim();
                    String lName = lNameInput.getText().toString().trim();
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
                            } else if (response.code() == 404) {
                                //Toast.makeText(CreateAccountActivity.this, "Wrong Credentials!", Toast.LENGTH_LONG).show();
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(CreateAccountActivity.this);
                                builder1.setMessage(valid_email + " is used.");
                                builder1.setCancelable(true);
                                AlertDialog alert11 = builder1.create();
                                alert11.show();
                            }
                        }


                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(CreateAccountActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else{
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(CreateAccountActivity.this);
                    builder1.setMessage("Email Not Valid");
                    builder1.setCancelable(true);
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }


            }
        });
    }
    public void loginTextView (View v){
        Intent intent = new Intent(v.getContext(),LoginActivity.class);
        startActivity(intent);
    }
}