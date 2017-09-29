package com.example.adityapattani.gitfit;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Workouts extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Workouts");
        viewPager = (ViewPager) findViewById(R.id.viewpager_workouts);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_workouts);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        Intent intent = getIntent();
        String tabName = intent.getStringExtra("Name");
        tabLayout.getTabAt(2).select();

        /*int tabIndex = 0;
        switch (tabName){
            case "Hands":
                tabIndex = 0;
                break;
            case "Abs":
                tabIndex = 1;
                Toast.makeText(getApplicationContext(), Integer.toString(tabIndex), Toast.LENGTH_SHORT).show();
                break;
            case "Legs":
                tabIndex = 2;
                Toast.makeText(getApplicationContext(), Integer.toString(tabIndex), Toast.LENGTH_SHORT).show();
                break;
        }
        tabLayout.getTabAt(tabIndex).select();*/
        setupViewPager(viewPager);
    }

    public void setupViewPager(ViewPager ViewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Hands(),"Hands");
        adapter.addFragment(new Abs(),"Abs");
        adapter.addFragment(new Legs(),"Legs");

        ViewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> FragmentList = new ArrayList<>();
        private final List<String> FragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentList.get(position);
        }

        @Override
        public int getCount() {
            return FragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            FragmentList.add(fragment);
            FragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return FragmentTitleList.get(position);
        }
    }
}
