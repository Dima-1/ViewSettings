package com.example.user.viewsettings;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.user.viewsettings", appContext.getPackageName());
    }
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(SettingsActivity.class);

    @Test
    public void testViewSettingsDisplayed() {
        onView(withText(R.string.pref_header_general)).check(matches(isDisplayed()));
        onView(withText(R.string.pref_header_general)).perform(click());
    }

    @Test
    public void testGeneralDisplayed() {
        testViewSettingsDisplayed();
        onView(withText(R.string.pref_title_font_size)).check(matches(isDisplayed()));
        onView(withText(R.string.pref_title_font_size)).perform(click());
    }
}
