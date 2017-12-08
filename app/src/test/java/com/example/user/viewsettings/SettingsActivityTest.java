package com.example.user.viewsettings;

import android.preference.PreferenceFragment;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

public class SettingsActivityTest extends  ActivityInstrumentationTestCase2 {

    public SettingsActivityTest() {
        super("com.example.user.viewsettings", SettingsActivity.class);
    }

    public void testIsValidFragment() throws Exception {
        PreferenceFragment preferenceFragment=new PreferenceFragment() {
        };
        PreferenceFragment.class.getName().equals(preferenceFragment);

    }

}