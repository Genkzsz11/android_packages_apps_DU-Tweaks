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

import com.dirtyunicorns.tweaks.fragments.system.Notifications;
import com.dirtyunicorns.tweaks.fragments.system.Miscellaneous;

public class System extends SettingsPreferenceFragment implements View.OnClickListener, Preference.OnPreferenceChangeListener {

    private static final String NOTIFICATIONS_CATEGORY = "notifications_category";
    private static final String MISC_CATEGORY = "miscellaneous_category";

    private MenuViews notif, miscellaneous;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment mFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.system, container, false);
        mFragmentManager = getActivity().getSupportFragmentManager();
        return view;
    }

    private void initViews(final View view) {
        notif = (MenuViews) view.findViewById(R.id.notif);
        miscellaneous = (MenuViews) view.findViewById(R.id.miscellaneous);
        initClick();
    }

    private void initClick() {
        notif.setOnClickListener(this);
        miscellaneous.setOnClickListener(this);
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
            case R.id.notif:
                loadFragment (NOTIFICATIONS_CATEGORY, true, null, new Notifications());
                break;
            case R.id.miscellaneous:
                loadFragment (MISC_CATEGORY, true, null, new Miscellaneous());
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
