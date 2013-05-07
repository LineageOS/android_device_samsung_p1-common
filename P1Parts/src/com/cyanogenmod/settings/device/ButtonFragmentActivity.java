/*
 * Copyright (C) 2012 The CyanogenMod Project
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

package com.cyanogenmod.settings.device;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;

import com.cyanogenmod.settings.device.R;

public class ButtonFragmentActivity extends PreferenceFragment {

    private static final String PREF_ENABLED = "1";
    private static final String TAG = "P1Parts_Button";

    private CheckBoxPreference mDisableButtons;
    private ListPreference mBacklightTimeout;
    private ListPreference mKeyLedBrightness;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.button_preferences);

        PreferenceScreen prefSet = getPreferenceScreen();

        mDisableButtons = (CheckBoxPreference) findPreference(DeviceSettings.KEY_BUTTONS_DISABLE);
        mDisableButtons.setEnabled(ToggleCapacitiveKeys.isSupported());
        mDisableButtons.setOnPreferenceChangeListener(new ToggleCapacitiveKeys());

        mBacklightTimeout = (ListPreference) findPreference(DeviceSettings.KEY_BACKLIGHT_TIMEOUT);
        mBacklightTimeout.setEnabled(TouchKeyBacklightTimeout.isSupported());
        mBacklightTimeout.setOnPreferenceChangeListener(new TouchKeyBacklightTimeout());
        TouchKeyBacklightTimeout.updateSummary(mBacklightTimeout,
                Integer.parseInt(mBacklightTimeout.getValue()));

        mKeyLedBrightness = (ListPreference) findPreference(DeviceSettings.KEY_LED_BRIGHTNESS);
        mKeyLedBrightness.setEnabled(KeyLedBrightness.isSupported());
        mKeyLedBrightness.setOnPreferenceChangeListener(new KeyLedBrightness());
        KeyLedBrightness.updateSummary(mKeyLedBrightness,
                Integer.parseInt(mKeyLedBrightness.getValue()));
    }

    public static void restore(Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }
}
