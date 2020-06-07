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

import com.dirtyunicorns.tweaks.fragments.lockscreen.LockscreenItems;
import com.dirtyunicorns.tweaks.fragments.lockscreen.FingerprintPrefs;

public class Lockscreen extends SettingsPreferenceFragment implements View.OnClickListener, Preference.OnPreferenceChangeListener {

    private static final String LOCKSCREEN_ITEMS_CATEGORY = "lockscreen_items_category";
    private static final String FINGERPRINT_PREFS_CATEGORY = "fingerprint_prefs_category";

    private MenuViews notif, miscellaneous;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment mFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lockscreen, container, false);
        mFragmentManager = getActivity().getSupportFragmentManager();
        return view;
    }

    private void initViews(final View view) {
        lockitem = (MenuViews) view.findViewById(R.id.lockscreen_items);
        fp = (MenuViews) view.findViewById(R.id.fingerprintprefs);
        initClick();
    }

    private void initClick() {
        lockitem.setOnClickListener(this);
        fp.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lockscreen_items:
                loadFragment (LOCKSCREEN_ITEMS_CATEGORY, true, null, new LockscreenItems());
                break;
            case R.id.fingerprintprefs:
                loadFragment (FINGERPRINT_PREFS_CATEGORY, true, null, new FingerprintPrefs());
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
