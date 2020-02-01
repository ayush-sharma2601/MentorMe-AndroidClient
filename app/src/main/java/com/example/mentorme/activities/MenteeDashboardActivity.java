package com.example.mentorme.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentorme.R;
import com.example.mentorme.SessionManager;
import com.example.mentorme.adapters.RecommendedSubjectsRVAdapter;
import com.example.mentorme.models.RecommendedSubjectsModel;
import com.google.android.material.navigation.NavigationView;

import java.util.Vector;

public class MenteeDashboardActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    private TextView textViewActionBarTitle;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private RecyclerView recyclerViewRecommendedSubjects;
    private RecommendedSubjectsRVAdapter recommendedSubjectsRVAdapter;
    private Vector<RecommendedSubjectsModel> recommendedSubjectsModels = new Vector<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menteedashboard);

        drawerLayout = findViewById(R.id.navigationDrawerLayout);
        navigationView = findViewById(R.id.navigationDrawer);

        recyclerViewRecommendedSubjects = findViewById(R.id.rvRecommendedSubjects);

        sessionManager = new SessionManager(this);

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

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menuItemMentorList:
                        Intent intent = new Intent(MenteeDashboardActivity.this, FragmentContainerActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.menuItemDeleteAccount:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MenteeDashboardActivity.this);
                        builder.setTitle("Are you sure?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteAccount(getIntent().getStringExtra("userId"), getIntent().getStringExtra("authToken"));
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                        break;
                }

                return false;
            }
        });

        RecommendedSubjectsModel recommendedSubjectsModel1 = new RecommendedSubjectsModel("Angular", "Angular is a TypeScript-based open-source web application framework led by the Angular Team at Google and by a community of individuals and corporations. Angular is a complete rewrite from the same team that built AngularJS.", 0);
        recommendedSubjectsModels.add(recommendedSubjectsModel1);

        RecommendedSubjectsModel recommendedSubjectsModel2 = new RecommendedSubjectsModel("jQuery", "jQuery is a JavaScript library designed to simplify HTML DOM tree traversal and manipulation, as well as event handling, CSS animation, and Ajax. It is free, open-source software using the permissive MIT License. As of May 2019, jQuery is used by 73% of the 10 million most popular websites.", 1);
        recommendedSubjectsModels.add(recommendedSubjectsModel2);

        RecommendedSubjectsModel recommendedSubjectsModel3 = new RecommendedSubjectsModel("Polymer", "Polymer is an open-source JavaScript library for building web applications using Web Components. The library is being developed by Google developers and contributors on GitHub. Modern design principles are implemented as a separate project using Google's Material Design design principles.", 2);
        recommendedSubjectsModels.add(recommendedSubjectsModel3);

        RecommendedSubjectsModel recommendedSubjectsModel4 = new RecommendedSubjectsModel("React", "React is a JavaScript library for building user interfaces. It is maintained by Facebook and a community of individual developers and companies. React can be used as a base in the development of single-page or mobile applications.", 3);
        recommendedSubjectsModels.add(recommendedSubjectsModel4);

        RecommendedSubjectsModel recommendedSubjectsModel5 = new RecommendedSubjectsModel("Vue.js", "Vue.js is an open-source Model–view–viewmodel JavaScript framework for building user interfaces and single-page applications. It was created by Evan You, and is maintained by him and the rest of the active core team members coming from various companies such as Netlify and Netguru.", 4);
        recommendedSubjectsModels.add(recommendedSubjectsModel5);

        RecommendedSubjectsModel recommendedSubjectsModel6 = new RecommendedSubjectsModel("Express.js", "Express.js, or simply Express, is a web application framework for Node.js, released as free and open-source software under the MIT License. It is designed for building web applications and APIs. It has been called the de facto standard server framework for Node.js.", 5);
        recommendedSubjectsModels.add(recommendedSubjectsModel6);

        RecommendedSubjectsModel recommendedSubjectsModel7 = new RecommendedSubjectsModel("Laravel", "Laravel is a free, open-source PHP web framework, created by Taylor Otwell and intended for the development of web applications following the model–view–controller architectural pattern and based on Symfony.", 6);
        recommendedSubjectsModels.add(recommendedSubjectsModel7);

        RecommendedSubjectsModel recommendedSubjectsModel8 = new RecommendedSubjectsModel("Node.js", "Node.js is an open-source, cross-platform, JavaScript runtime environment that executes JavaScript code outside of a browser.", 7);
        recommendedSubjectsModels.add(recommendedSubjectsModel8);

        RecommendedSubjectsModel recommendedSubjectsModel9 = new RecommendedSubjectsModel("Python", "Python is an interpreted, high-level, general-purpose programming language. Created by Guido van Rossum and first released in 1991, Python's design philosophy emphasizes code readability with its notable use of significant whitespace.", 8);
        recommendedSubjectsModels.add(recommendedSubjectsModel9);

        RecommendedSubjectsModel recommendedSubjectsModel10 = new RecommendedSubjectsModel("TensorFlow", "TensorFlow is a free and open-source software library for dataflow and differentiable programming across a range of tasks. It is a symbolic math library, and is also used for machine learning applications such as neural networks.", 9);
        recommendedSubjectsModels.add(recommendedSubjectsModel10);

        RecommendedSubjectsModel recommendedSubjectsModel11 = new RecommendedSubjectsModel("C", "C is a general-purpose, procedural computer programming language supporting structured programming, lexical variable scope, and recursion, while a static type system prevents unintended operations.", 10);
        recommendedSubjectsModels.add(recommendedSubjectsModel11);

        RecommendedSubjectsModel recommendedSubjectsModel12 = new RecommendedSubjectsModel("C++", "C++ is a general-purpose programming language created by Bjarne Stroustrup as an extension of the C programming language, or \"C with Classes\". ", 11);
        recommendedSubjectsModels.add(recommendedSubjectsModel12);

        RecommendedSubjectsModel recommendedSubjectsModel13 = new RecommendedSubjectsModel("Java", "ava is a general-purpose programming language that is class-based, object-oriented, and designed to have as few implementation dependencies as possible.", 12);
        recommendedSubjectsModels.add(recommendedSubjectsModel13);

        RecommendedSubjectsModel recommendedSubjectsModel14 = new RecommendedSubjectsModel("PHP", "PHP is a general-purpose programming language originally designed for web development. It was originally created by Rasmus Lerdorf in 1994; the PHP reference implementation is now produced by The PHP Group.", 13);
        recommendedSubjectsModels.add(recommendedSubjectsModel14);

        RecommendedSubjectsModel recommendedSubjectsModel15 = new RecommendedSubjectsModel("JavaScript", "JavaScript, often abbreviated as JS, is a high-level, just-in-time compiled, multi-paradigm programming language that conforms to the ECMAScript specification. JavaScript has curly-bracket syntax, dynamic typing, prototype-based object-orientation, and first-class functions. ", 14);
        recommendedSubjectsModels.add(recommendedSubjectsModel15);

        RecommendedSubjectsModel recommendedSubjectsModel16 = new RecommendedSubjectsModel("R", "R is a programming language and free software environment for statistical computing and graphics supported by the R Foundation for Statistical Computing. The R language is widely used among statisticians and data miners for developing statistical software and data analysis. ", 15);
        recommendedSubjectsModels.add(recommendedSubjectsModel16);

        recommendedSubjectsRVAdapter = new RecommendedSubjectsRVAdapter(recommendedSubjectsModels, this);
        recyclerViewRecommendedSubjects.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerViewRecommendedSubjects.setAdapter(recommendedSubjectsRVAdapter);
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView))
            drawerLayout.closeDrawers();
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("What do you want to do?");
            builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sessionManager.logoutUser();
                    startActivity(new Intent(MenteeDashboardActivity.this, RoleSelectionActivity.class));
                }
            });
            builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MenteeDashboardActivity.super.onBackPressed();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    private void deleteAccount(String userId, String authToken) {
        Toast.makeText(this, "Delete account feature coming soon.", Toast.LENGTH_SHORT).show();
    }
}
