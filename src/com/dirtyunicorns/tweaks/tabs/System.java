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

import com.dirtyunicorns.tweaks.fragments.system.Notifications;
import com.dirtyunicorns.tweaks.fragments.system.Miscellaneous;

public class System extends Fragment implements View.OnClickListener {

    GridLayout mMainGrid;
    MaterialCardView mNotifications;
    MaterialCardView mMiscellaneous;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.system, container, false);
        mMainGrid = view.findViewById(R.id.system_grid);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNotifications = (MaterialCardView) view.findViewById(R.id.notif);
        mNotifications.setOnClickListener(this);

        mMiscellaneous = (MaterialCardView) view.findViewById(R.id.miscellaneous);
        mMiscellaneous.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.notif:
                Notifications notif = new Notifications();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.replace(this.getId(), notif);
                transaction.commit();
                break;
            case R.id.miscellaneous:
                Miscellaneous misc = new Miscellaneous();
                FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                transaction1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction1.replace(this.getId(), misc);
                transaction1.commit();
                break;
        }
    }

    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.DIRTYTWEAKS;
    }
}
