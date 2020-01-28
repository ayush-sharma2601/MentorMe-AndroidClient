package com.example.mentorme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mentorme.R;
import com.example.mentorme.api.RetrofitClient;
import com.example.mentorme.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName, editTextEmail, editTextPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignUp:
                userSignUp();
                break;

            case R.id.textViewLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    private void userSignUp() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError("Name is required");
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password should be at least 6 character long");
            editTextPassword.requestFocus();
            return;
        }

        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(name, email, password);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 200) {
                    DefaultResponse defaultResponse = response.body();
                    if (defaultResponse.getSuccess().equals("true")) {
                        Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                        intent.putExtra("userId",defaultResponse.getUserId());
                        intent.putExtra("authToken",defaultResponse.getAuthToken());
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(SignUpActivity.this, defaultResponse.getMsg(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(SignUpActivity.this, "Unknown error\nTry again", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Connection error\nTry again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
