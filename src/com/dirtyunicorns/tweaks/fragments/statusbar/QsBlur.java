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

package com.dirtyunicorns.tweaks.fragments.statusbar;

import android.content.Context;
import android.content.Intent;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import androidx.preference.*;

import com.android.internal.logging.nano.MetricsProto;

import com.android.settings.R;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;
import com.android.settingslib.search.SearchIndexable;

import com.dirtyunicorns.support.preferences.CustomSeekBarPreference;

import java.util.ArrayList;
import java.util.List;

@SearchIndexable
public class QsBlur extends SettingsPreferenceFragment
        implements Preference.OnPreferenceChangeListener, Indexable {

    private static final String QS_BLUR_ALPHA = "qs_blur_alpha";
    private static final String QS_BLUR_INTENSITY = "qs_blur_intensity";

    private CustomSeekBarPreference mQSBlurAlpha;
    private CustomSeekBarPreference mQsBlurIntensity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.qs_blur);

        ContentResolver resolver = getActivity().getContentResolver();

        mQSBlurAlpha = (CustomSeekBarPreference) findPreference(QS_BLUR_ALPHA);
        int qsBlurAlpha = Settings.System.getInt(getContentResolver(),
                Settings.System.QS_BLUR_ALPHA, 100);
        mQSBlurAlpha.setValue(qsBlurAlpha);
        mQSBlurAlpha.setOnPreferenceChangeListener(this);

        mQsBlurIntensity = (CustomSeekBarPreference) findPreference(QS_BLUR_INTENSITY);
        int qsBlurIntensity = Settings.System.getIntForUser(resolver,
                Settings.System.QS_BLUR_INTENSITY, 100, UserHandle.USER_CURRENT);
        mQsBlurIntensity.setValue(qsBlurIntensity);
        mQsBlurIntensity.setOnPreferenceChangeListener(this);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
	if (preference == mQSBlurAlpha) {
            int value = (Integer) newValue;
            Settings.System.putInt(getContentResolver(),
                    Settings.System.QS_BLUR_ALPHA, value);
            return true;
        } else if (preference == mQsBlurIntensity) {
            int valueInt = (Integer) newValue;
            Settings.System.putInt(getContentResolver(),
                    Settings.System.QS_BLUR_INTENSITY, valueInt);
            return true;
        }
        return false;
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.DIRTYTWEAKS;
    }

    public static final SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider() {
                @Override
                public List<SearchIndexableResource> getXmlResourcesToIndex(Context context,
                        boolean enabled) {
                    ArrayList<SearchIndexableResource> result =
                            new ArrayList<SearchIndexableResource>();

                    SearchIndexableResource sir = new SearchIndexableResource(context);
                    sir.xmlResId = R.xml.qs_blur;
                    result.add(sir);
                    return result;
                }

                @Override
                public List<String> getNonIndexableKeys(Context context) {
                    List<String> keys = super.getNonIndexableKeys(context);
                    return keys;
        }
    };
}
