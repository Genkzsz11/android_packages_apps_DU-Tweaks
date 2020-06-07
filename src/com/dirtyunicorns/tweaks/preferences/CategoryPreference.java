/*
 * Copyright (C) 2015 The Android Open Source Project
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

package com.dirtyunicorns.tweaks.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import androidx.cardview.widget.CardView;
import android.widget.GridLayout;
import android.widget.GridView;
import com.android.settings.R;
import com.android.settings.Utils;

public class CategoryPreference extends Preference implements View.OnClickListener {

    private final View.OnClickListener mClickListener = v -> performClick(v);

    GridLayout gridLayout;
    CardView cardView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.category_preference, container, false);
        gridLayout = (GridLayout) view.findViewById (R.id.main_grid);
        cardView = (CardView) view.findViewById(R.id.perfCard);
        return view;
    }

    cardview.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        v.itemView.setOnClickListener(mClickListener);
        }
    });
 } 
