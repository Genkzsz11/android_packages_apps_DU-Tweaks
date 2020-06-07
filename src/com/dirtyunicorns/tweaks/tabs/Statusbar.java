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

import com.google.android.material.card.MaterialCardView;
import android.widget.GridLayout;
import android.widget.GridView;
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

public class Statusbar extends Fragment implements View.OnClickListener {

    GridLayout mMainGrid;

    MaterialCardView mBatteryOptions;
    MaterialCardView mCarrierLabel;
    MaterialCardView mClockOptions;
    MaterialCardView mIconManager;
    MaterialCardView mQuickSettings;
    MaterialCardView mTraffic;
    MaterialCardView mTicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statusbar, container, false);
        mMainGrid = view.findViewById(R.id.statusbar_grid);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBatteryOptions = (MaterialCardView) view.findViewById(R.id.battery_options);
        mBatteryOptions.setOnClickListener(this);

        mCarrierLabel = (MaterialCardView) view.findViewById(R.id.carrier_label);
        mCarrierLabel.setOnClickListener(this);

        mClockOptions = (MaterialCardView) view.findViewById(R.id.clock_options);
        mClockOptions.setOnClickListener(this);

        mIconManager = (MaterialCardView) view.findViewById(R.id.icon_manager);
        mIconManager.setOnClickListener(this);

        mQuickSettings = (MaterialCardView) view.findViewById(R.id.quick_settings);
        mQuickSettings.setOnClickListener(this);

        mTraffic = (MaterialCardView) view.findViewById(R.id.traffic_indicator);
        mTraffic.setOnClickListener(this);

        mTicker = (MaterialCardView) view.findViewById(R.id.ticker);
        mTicker.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.battery_options:
                BatteryOptions battery = new BatteryOptions();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.replace(this.getId(), battery);
                transaction.commit();
                break;
            case R.id.carrier_label:
                CarrierLabel carrier = new CarrierLabel();
                FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                transaction1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction1.replace(this.getId(), carrier);
                transaction1.commit();
                break;
            case R.id.clock_options:
                ClockOptions clock = new ClockOptions();
                FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                transaction2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction2.replace(this.getId(), clock);
                transaction2.commit();
                break;
            case R.id.icon_manager:
                IconManager iconmanager = new IconManager();
                FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
                transaction3.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction3.replace(this.getId(), iconmanager);
                transaction3.commit();
                break;
            case R.id.quick_settings:
                QuickSettings qs = new QuickSettings();
                FragmentTransaction transaction4 = getFragmentManager().beginTransaction();
                transaction4.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction4.replace(this.getId(), qs);
                transaction4.commit();
                break;
            case R.id.traffic_indicator:
                TrafficIndicators traffic = new TrafficIndicators();
                FragmentTransaction transaction5 = getFragmentManager().beginTransaction();
                transaction5.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction5.replace(this.getId(), traffic);
                transaction5.commit();
                break;
            case R.id.ticker:
                Ticker ticker = new Ticker();
                FragmentTransaction transaction6 = getFragmentManager().beginTransaction();
                transaction6.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction6.replace(this.getId(), ticker);
                transaction6.commit();
                break;
        }
    }

    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.DIRTYTWEAKS;
    }
}
