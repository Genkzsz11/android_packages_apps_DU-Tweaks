/*
 * Copyright (C) 2014-2016 The Dirty Unicorns Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dirtyunicorns.tweaks;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.provider.Settings;
import android.graphics.Color;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceManager;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.ViewPager;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.dirtyunicorns.tweaks.preferences.TabBubbleAnimator;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto;

import com.dirtyunicorns.tweaks.fragments.team.TeamActivity;
import com.dirtyunicorns.tweaks.tabs.Lockscreen;
import com.dirtyunicorns.tweaks.tabs.Hardware;
import com.dirtyunicorns.tweaks.tabs.Statusbar;
import com.dirtyunicorns.tweaks.tabs.System;

public class DirtyTweaks extends SettingsPreferenceFragment {

    private String title;
    private int colorId;
    private MenuItem mMenuItem;
    private Context mContext;
    private TabBubbleAnimator tabBubbleAnimator;
    private String[] titles = new String[]{"System", "Lockscreen", "Statusbar", "Hardware"};
    private int[] colors = new int[]{R.color.system, R.color.lockscreen, R.color.statusbar, R.color.hardware};
    private List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        mContext = getActivity();
        getActivity().setTitle(R.string.dirtytweaks_title);
        setContentView(R.layout.dirtytweaks);
        mFragmentList.add(new System(titles[0], colors[0]));
        mFragmentList.add(new Lockscreen(titles[1], colors[1]));
        mFragmentList.add(new Statusbar(titles[2], colors[2]));
        mFragmentList.add(new Hardware(titles[3], colors[3]));
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getFragmentManager()) {
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
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
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
    public void onStart() {
        super.onStart();
        tabBubbleAnimator.onStart((TabLayout) view.findViewById(R.id.tabLayout));
    }

    @Override
    public void onStop() {
        super.onStop();
        tabBubbleAnimator.onStop();
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.DIRTYTWEAKS;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.add(0, 0, 0, R.string.dialog_team_title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Intent intent = new Intent(mContext, TeamActivity.class);
                mContext.startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}
