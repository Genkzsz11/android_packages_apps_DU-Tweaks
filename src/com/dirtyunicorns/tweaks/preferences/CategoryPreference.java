package com.dirtyunicorns.tweaks.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

import com.android.settings.R;
import com.android.settings.Utils;

public class CategoryPreference extends Preference {

    private final View.OnClickListener mClickListener = v -> performClick(v);

    private boolean mAllowDividerAbove;
    private boolean mAllowDividerBelow;

    public CategoryPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Preference);
        a.recycle();

        setLayoutResource(R.layout.category_preference);
    }

    public CategoryPreference(Context context, View view) {
        super(context);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        holder.itemView.setOnClickListener(mClickListener);
        final boolean selectable = isSelectable();
        holder.itemView.setFocusable(selectable);
        holder.itemView.setClickable(selectable);
    }
}
