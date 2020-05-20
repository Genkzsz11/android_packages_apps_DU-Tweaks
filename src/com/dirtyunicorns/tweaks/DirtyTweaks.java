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
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceManager;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.ViewPager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto;

import com.dirtyunicorns.tweaks.tabs.TabBubbleAnimator;
import com.google.android.material.tabs.TabLayout;

import com.dirtyunicorns.tweaks.fragments.team.TeamActivity;
import com.dirtyunicorns.tweaks.tabs.Lockscreen;
import com.dirtyunicorns.tweaks.tabs.Hardware;
import com.dirtyunicorns.tweaks.tabs.Statusbar;
import com.dirtyunicorns.tweaks.tabs.System;

import java.util.ArrayList;
import java.util.List;


public class DirtyTweaks extends SettingsPreferenceFragment implements   
       Preference.OnPreferenceChangeListener {

    private MenuItem mMenuItem;
    private Context mContext;
    private ViewPager mViewpager;
    private TabBubbleAnimator mTabBubbleAnimator;
    private TabLayout mTabLayout;
    private String[] titles = new String[]{"System", "Lockscreen", "Statusbar", "Hardware"};
    private int[] colors = new int[]{R.color.system, R.color.lockscreen, R.color.statusbar, R.color.hardware};
    private List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();
        View view = inflater.inflate(R.layout.dirtytweaks, container, false);

        getActivity().setTitle(R.string.dirtytweaks_title);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mTabLayout.setViewPager(mViewpager);
        mTabBubbleAnimator = new TabBubbleAnimator(mTabLayout);
        PagerAdapter.setAdapter(new PagerAdapter(getFragmentManager()));
        mViewpager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewpager.addOnPageChangeListener(mTabBubbleAnimator);
        oninit();
        return view;
    }

    public void onInit() {
        mTabBubbleAnimator.setUnselectedColorId(Color.BLACK);
        mTabBubbleAnimator.addTabItem(titles[0], R.drawable.bottomnav_system, colors[0]);
        mTabBubbleAnimator.addTabItem(titles[1], R.drawable.bottomnav_lockscreen, colors[1]);
        mTabBubbleAnimator.addTabItem(titles[2], R.drawable.bottomnav_statusbar, colors[2]);
        mTabBubbleAnimator.addTabItem(titles[3], R.drawable.bottomnav_hardware, colors[3]);
        mTabBubbleAnimator.highLightTab(0);
    }

    class PagerAdapter extends FragmentPagerAdapter {

        PagerAdapter(FragmentManager fm) {
            super(fm);
        mFragmentList.add(new System(titles[0], colors[0]));
        mFragmentList.add(new Lockscreen(titles[1],colors[1]));
        mFragmentList.add(new Statusbar(titles[2], colors[2]));
        mFragmentList.add(new Hardware(titles[3], colors[3]));
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }

    public boolean onPreferenceChange(Preference preference, Object objValue) {
        final String key = preference.getKey();
        return true;
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
