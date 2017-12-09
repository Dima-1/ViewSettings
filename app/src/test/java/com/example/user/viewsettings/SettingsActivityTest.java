package com.example.user.viewsettings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;

public class SettingsActivityTest {

    @Test
    public void test1()  {
        SettingsActivity test = spy(SettingsActivity.class);
        SettingsActivity.GeneralPreferenceFragment generalPreferenceFragment=
                new SettingsActivity.GeneralPreferenceFragment();
        SettingsActivity.NotificationPreferenceFragment notificationPreferenceFragment=
                new SettingsActivity.NotificationPreferenceFragment();
        SettingsActivity.DataSyncPreferenceFragment dataSyncPreferenceFragment=
                new SettingsActivity.DataSyncPreferenceFragment();
        String string="";

        // use mock in test....
        assertEquals(true,test.isValidFragment(generalPreferenceFragment.getClass().getName()));
        assertEquals(true,test.isValidFragment(notificationPreferenceFragment.getClass().getName()));
        assertEquals(true,test.isValidFragment(dataSyncPreferenceFragment.getClass().getName()));
        assertEquals(false,test.isValidFragment(string.getClass().getName()));
    }
}