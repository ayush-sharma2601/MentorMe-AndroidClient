package com.example.mentorme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mentorme.R;
import com.example.mentorme.SessionManager;

public class RoleSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    private SessionManager sessionManager;

    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roleselection);

        findViewById(R.id.buttonMentee).setOnClickListener(this);
        findViewById(R.id.buttonMentor).setOnClickListener(this);

        sessionManager = new SessionManager(this);

        if (sessionManager.getLogInStatus()) {
            if (sessionManager.getRoleSelected().equals("mentee")) {
                startActivity(new Intent(this, MenteeDashboardActivity.class));
                finish();
            }
            else {
                Toast.makeText(this, "Mentor features coming soon.", Toast.LENGTH_SHORT).show();
                // Redirect to MenteeListActivity
                finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonMentee:
                sessionManager.setRoleSelected("mentee");
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.buttonMentor:
                sessionManager.setRoleSelected("mentor");
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
