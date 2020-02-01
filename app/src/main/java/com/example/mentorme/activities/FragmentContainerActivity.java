package com.example.mentorme.activities;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mentorme.R;
import com.example.mentorme.SessionManager;
import com.example.mentorme.fragments.ListFragment;
import com.example.mentorme.fragments.OnGoingChatsFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class FragmentContainerActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    private TextView textViewActionBarTitle;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

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

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        String fragmentName = sessionManager.getRoleSelected().equals("mentee") ? "Mentors" : "Mentees";

        viewPagerAdapter.addFragment(new ListFragment(), fragmentName+" List");
        //viewPagerAdapter.addFragment(new OnGoingChatsFragment(), "Chats");

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
