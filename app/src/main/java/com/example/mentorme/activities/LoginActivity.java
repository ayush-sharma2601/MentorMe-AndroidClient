package com.example.mentorme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mentorme.R;
import com.example.mentorme.api.RetrofitClient;
import com.example.mentorme.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.textViewSignUp).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                userLogin();
                break;
            case R.id.textViewSignUp:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

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

        Call<LoginResponse> call = RetrofitClient
                .getInstance().getApi().userLogin(email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (response.code() == 200) {
                    if (loginResponse.getSuccess().equals("true")) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("usedId", loginResponse.getUserId());
                        intent.putExtra("authToken", loginResponse.getAuthToken());
                        startActivity(intent);
                    } else
                        Toast.makeText(LoginActivity.this, loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(LoginActivity.this, "Unknown error\nTry again", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Connection error\nTry again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
