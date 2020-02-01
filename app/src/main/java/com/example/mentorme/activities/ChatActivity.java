package com.example.mentorme.activities;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mentorme.R;

public class ChatActivity extends AppCompatActivity {

    private TextView textViewActionBarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar));

            textViewActionBarTitle = new TextView(this);
            textViewActionBarTitle.setText(actionBar.getTitle());
            textViewActionBarTitle.setTextSize(22);
            textViewActionBarTitle.setTextColor(Color.WHITE);
            textViewActionBarTitle.setTypeface(Typeface.MONOSPACE);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(textViewActionBarTitle);
        }

    }
}
