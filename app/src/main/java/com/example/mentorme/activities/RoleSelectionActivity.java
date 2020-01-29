package com.example.mentorme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mentorme.R;

public class RoleSelectionActivity extends AppCompatActivity implements View.OnClickListener{

    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roleselection);

        findViewById(R.id.buttonMentee).setOnClickListener(this);
        findViewById(R.id.buttonMentor).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonMentee:
                intent = new Intent(this,LoginActivity.class);
                intent.putExtra("roleSelected","mentee");
                startActivity(intent);
                break;
            case R.id.buttonMentor:
                intent = new Intent(this,LoginActivity.class);
                intent.putExtra("roleSelected","mentor");
                startActivity(intent);
                break;
        }
    }
}
