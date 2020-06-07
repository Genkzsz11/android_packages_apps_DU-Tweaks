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
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.settings.R;
import com.android.internal.logging.nano.MetricsProto;

import com.dirtyunicorns.tweaks.fragments.statusbar.BatteryOptions;
import com.dirtyunicorns.tweaks.fragments.statusbar.CarrierLabel;
import com.dirtyunicorns.tweaks.fragments.statusbar.ClockOptions;
import com.dirtyunicorns.tweaks.fragments.statusbar.IconManager;
import com.dirtyunicorns.tweaks.fragments.statusbar.QuickSettings;
import com.dirtyunicorns.tweaks.fragments.statusbar.TrafficIndicators;
import com.dirtyunicorns.tweaks.fragments.statusbar.Ticker;

public class Statusbar extends SettingsPreferenceFragment implements View.OnClickListener, Preference.OnPreferenceChangeListener {

    private static final String BATTERY_CATEGORY = "battery_options_category";
    private static final String CARRIER_LABEL_CATEGORY = "carrier_label_category";
    private static final String CLOCK_CATEGORY = "clock_options_category";
    private static final String ICON_MANAGER_CATEGORY = "icon_manager_title";
    private static final String QUICK_SETTINGS_CATEGORY = "quick_settings_category";
    private static final String TRAFFIC_CATEGORY = "traffic_category";
    private static final String TICKER_CATEGORY = "ticker_category";

    private MenuViews battery, carrierlabel, clock, icon, qs, nettraff, ticker;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment mFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statusbar, container, false);
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
        battery = (MenuViews) view.findViewById(R.id.battery_options);
        carrierlabel = (MenuViews) view.findViewById(R.id.carrier_label);
        clock = (MenuViews) view.findViewById(R.id.clock_options);
        icon = (MenuViews) view.findViewById(R.id.icon_manager);
        qs = (MenuViews) view.findViewById(R.id.quick_settings);
        nettraff = (MenuViews) view.findViewById(R.id.traffic_indicator);
        ticker = (MenuViews) view.findViewById(R.id.ticker);
        initClick();
    }

    private void initClick() {
        battery.setOnClickListener(this);
        carrierlabel.setOnClickListener(this);
        clock.setOnClickListener(this);
        icon.setOnClickListener(this);
        qs.setOnClickListener(this);
        nettraff.setOnClickListener(this);
        ticker.setOnClickListener(this);
    }

    private void loadFragment(String tag, boolean addToStack, Bundle bundle, Fragment setFragment) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragment = setFragment;

        if (addToStack) {
          if (bundle != null)
            mFragment.setArguments(bundle);
            mFragmentTransaction.replace(R.id.fragment_statusbar, mFragment, tag);
            mFragmentTransaction.addToBackStack(tag).commit();

        } else {
          if (bundle != null)
              mFragment.setArguments(bundle);
              mFragmentTransaction.replace(R.id.fragment_statusbar, mFragment, tag);
              mFragmentTransaction.commit();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.battery_options:
                loadFragment (BATTERY_CATEGORY, true, null, new BatteryOptions());
                break;
            case R.id.carrier_label:
                loadFragment (CARRIER_LABEL_CATEGORY, true, null, new CarrierLabel());
                break;
            case R.id.clock_options:
                loadFragment (CLOCK_CATEGORY, true, null, new ClockOptions());
                break;
            case R.id.icon_manager:
                loadFragment (ICON_MANAGER_CATEGORY, true, null, new IconManager());
                break;
            case R.id.quick_settings:
                loadFragment (QUICK_SETTINGS_CATEGORY, true, null, new QuickSettings());
                break;
            case R.id.traffic_indicator:
                loadFragment (TRAFFIC_CATEGORY, true, null, new TrafficIndicators());
                break;
            case R.id.ticker:
                loadFragment (TICKER_CATEGORY, true, null, new Ticker());
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
