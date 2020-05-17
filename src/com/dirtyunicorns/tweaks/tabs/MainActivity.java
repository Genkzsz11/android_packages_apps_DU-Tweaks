package com.dirtyunicorns.tweaks.tab;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dirtyunicorns.tweaks.tabs.TabBubbleAnimator;
import com.google.android.material.tabs.TabLayout;

import com.dirtyunicorns.tweaks.tabs.Lockscreen;
import com.dirtyunicorns.tweaks.tabs.Hardware;
import com.dirtyunicorns.tweaks.tabs.Statusbar;
import com.dirtyunicorns.tweaks.tabs.System;

import com.dirtyunicorns.tweaks.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabBubbleAnimator tabBubbleAnimator;
    private String[] titles = new String[]{"System", "Lockscreen", "Statusbar", "Hardware"};
    private int[] colors = new int[]{R.color.system, R.color.lockscreen, R.color.statusbar, R.color.hardware};
    private List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dirtytweaks);
        mFragmentList.add(new System(titles[0], colors[0]));
        mFragmentList.add(new Lockscreen(titles[1], colors[1]));
        mFragmentList.add(new Statusbar(titles[2], colors[2]));
        mFragmentList.add(new Hardware(titles[3], colors[3]));
        ViewPager viewPager = findViewById(R.id.viewPager);
        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        };
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabBubbleAnimator = new TabBubbleAnimator(tabLayout);
        tabBubbleAnimator.addTabItem(titles[0], R.drawable.ic_system, colors[0]);
        tabBubbleAnimator.addTabItem(titles[1], R.drawable.ic_lockscreen,colors[1]);
        tabBubbleAnimator.addTabItem(titles[2], R.drawable.ic_statusbar, colors[2]);
        tabBubbleAnimator.addTabItem(titles[3], R.drawable.ic_hardware, colors[3]);
        tabBubbleAnimator.setUnselectedColorId(Color.BLACK);
        tabBubbleAnimator.highLightTab(0);
        viewPager.addOnPageChangeListener(tabBubbleAnimator);
    }

    @Override
    protected void onStart() {
        super.onStart();
        tabBubbleAnimator.onStart((TabLayout) findViewById(R.id.tabLayout));
    }

    @Override
    protected void onStop() {
        super.onStop();
        tabBubbleAnimator.onStop();
    }
}
