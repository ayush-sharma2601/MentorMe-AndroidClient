package com.example.mentorme.activities;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentorme.R;
import com.example.mentorme.adapters.RecommendedSubjectsRVAdapter;
import com.example.mentorme.models.RecommendedSubjectsModel;

import java.util.Vector;

public class MenteeDashboardActivity extends AppCompatActivity {

    private TextView textViewActionBarTitle;

    private RecyclerView recyclerViewRecommendedSubjects;
    private RecommendedSubjectsRVAdapter recommendedSubjectsRVAdapter;
    private Vector<RecommendedSubjectsModel> recommendedSubjectsModels = new Vector<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menteedashboard);

        recyclerViewRecommendedSubjects = findViewById(R.id.rvRecommendedSubjects);

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

        RecommendedSubjectsModel recommendedSubjectsModel = new RecommendedSubjectsModel("Android", "Android is a mobile operating system based on a modified version of the Linux kernel and other open source software, designed primarily for touchscreen mobile devices such as smartphones and tablets.");
        recommendedSubjectsModels.add(recommendedSubjectsModel);

        recommendedSubjectsRVAdapter = new RecommendedSubjectsRVAdapter(recommendedSubjectsModels, this);
        recyclerViewRecommendedSubjects.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerViewRecommendedSubjects.setAdapter(recommendedSubjectsRVAdapter);
    }
}
