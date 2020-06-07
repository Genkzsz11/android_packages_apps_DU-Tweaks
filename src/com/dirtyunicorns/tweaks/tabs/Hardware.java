/*
 * Copyright (C) 2017-2019 The Dirty Unicorns Project
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

package com.dirtyunicorns.tweaks.tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceManager;
import androidx.preference.Preference.OnPreferenceChangeListener;

import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto;

import com.dirtyunicorns.tweaks.fragments.hardware.Buttons;
import com.dirtyunicorns.tweaks.fragments.hardware.PowerMenu;
import com.dirtyunicorns.tweaks.fragments.hardware.NavigationOptions;

public class Hardware extends SettingsPreferenceFragment implements View.OnClickListener, Preference.OnPreferenceChangeListener {

    private static final String BUTTONS_CATEGORY = "buttons_category";
    private static final String NAVIGATION_CATEGORY = "navigation_category";
    private static final String POWERMENU_CATEGORY = "powermenu_category";

    private MenuViews buttons, powermenu, navigation;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment mFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hardware, container, false);
        mFragmentManager = getActivity().getSupportFragmentManager();
        initViews(view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initViews(final View view) {
        buttons = (MenuViews) view.findViewById(R.id.buttons);
        powermenu = (MenuViews) view.findViewById(R.id.powermenu);
        navigation = (MenuViews) view.findViewById(R.id.navigation_options);
        initClick();
    }

    private void initClick() {
        buttons.setOnClickListener(this);
        powermenu.setOnClickListener(this);
        navigation.setOnClickListener(this);
    }

    private void loadFragment(String tag, boolean addToStack, Bundle bundle, Fragment setFragment) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragment = setFragment;

        if (addToStack) {
          if (bundle != null)
            mFragment.setArguments(bundle);
            mFragmentTransaction.replace(R.id.fragment_hardware, mFragment, tag);
            mFragmentTransaction.addToBackStack(tag).commit();

        } else {
          if (bundle != null)
              mFragment.setArguments(bundle);
              mFragmentTransaction.replace(R.id.fragment_hardware, mFragment, tag);
              mFragmentTransaction.commit();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttons:
                loadFragment (BUTTONS_CATEGORY, true, null, new Buttons());
                break;
            case R.id.powermenu:
                loadFragment (NAVIGATION_CATEGORY, true, null, new PowerMenu());
                break;
            case R.id.navigation_options:
                loadFragment (POWERMENU_CATEGORY, true, null, new NavigationOptions());
                break;
        }
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.DIRTYTWEAKS;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public boolean onPreferenceChange(Preference preference, Object objValue) {
        final String key = preference.getKey();
        return true;
    }
}
