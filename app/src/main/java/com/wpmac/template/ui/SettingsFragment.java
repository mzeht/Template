package com.wpmac.template.ui;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.wpmac.template.R;

/**
 * Created by Administrator on 2017/7/26.
 */

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.app_preferences);
    }
}
