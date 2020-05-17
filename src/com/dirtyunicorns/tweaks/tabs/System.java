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
import android.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.android.internal.logging.nano.MetricsProto;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class System extends SettingsPreferenceFragment
        implements Preference.OnPreferenceChangeListener {

    private String title;
    private int colorId;

    private static final String CORVUS_PARTS_CATEGORY = "corvus_parts_category";
    private static final String NOTIFICATIONS_CATEGORY = "notifications_category";
    private static final String MISC_CATEGORY = "miscellaneous_category";

    public System(String title, int colorId) {
        this.title = title;
        this.colorId = colorId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.system);

        Preference CorvusParts = findPreference(CORVUS_PARTS_CATEGORY);
        if (!getResources().getBoolean(R.bool.has_corvus_parts_available)) {
            getPreferenceScreen().removePreference(CorvusParts);
        }

        Preference Notifications = findPreference(NOTIFICATIONS_CATEGORY);
        if (!getResources().getBoolean(R.bool.has_notifications)) {
            getPreferenceScreen().removePreference(Notifications);
        }

        Preference MiscOptions = findPreference("miscellaneous_category");
        if (!getResources().getBoolean(R.bool.has_misc_options)) {
            getPreferenceScreen().removePreference(MiscOptions);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = getView().findViewById(R.id.tab_title);
        textView.setText(title);
        textView.setTextColor(ContextCompat.getColor(getContext(), colorId));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public boolean onPreferenceChange(Preference preference, Object objValue) {
        final String key = preference.getKey();
        return false;
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.DIRTYTWEAKS;
    }
}
