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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import com.dirtyunicorns.tweaks.fragments.team.TeamActivity;
import com.dirtyunicorns.tweaks.tabs.Lockscreen;
import com.dirtyunicorns.tweaks.tabs.Hardware;
import com.dirtyunicorns.tweaks.tabs.Statusbar;
import com.dirtyunicorns.tweaks.tabs.System;
import com.dirtyunicorns.tweaks.bottomnav.BubbleNavigationConstraintView;
import com.dirtyunicorns.tweaks.bottomnav.BubbleNavigationChangeListener;

public class DirtyTweaks extends SettingsPreferenceFragment {

    private MenuItem mMenuItem;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();
        View view = inflater.inflate(R.layout.dirtytweaks, container, false);

        getActivity().setTitle(R.string.dirtytweaks_title);

        final BubbleNavigationConstraintView bubbleNavigationConstraintView =  (BubbleNavigationConstraintView) view.findViewById(R.id.bottom_navigation_view_constraint);
        final ViewPager viewPager = view.findViewById(R.id.viewpager);
        PagerAdapter mPagerAdapter = new PagerAdapter(getFragmentManager());
        viewPager.setAdapter(mPagerAdapter);

        Fragment system = new System();
        Fragment lockscreen = new Lockscreen();
        Fragment statusbar = new Statusbar();
        Fragment hardware = new Hardware();

        Fragment fragment = (Fragment) getFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, system);
            transaction.addToBackStack(null);
            transaction.commit();
        }

        bubbleNavigationConstraintView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch(view.getId()) {
                    case R.id.system:
                    launchFragment(system);
                    case R.id.lockscreen:
                    launchFragment(lockscreen);
                    case R.id.statusbar:
                    launchFragment(statusbar);
                    case R.id.hardware:
                    launchFragment(hardware);	
                    break;
                   }
               }
           });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                bubbleNavigationConstraintView.setCurrentActiveItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        viewPager.setCurrentItem(position, true);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    }

    private void launchFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
        if (item.getItemId() == 0) {
                Intent intent = new Intent(mContext, TeamActivity.class);
                mContext.startActivity(intent);
            return true;
        }
        return false;
    }
}
