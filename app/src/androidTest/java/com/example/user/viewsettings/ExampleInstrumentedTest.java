package com.example.user.viewsettings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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
    private Context context = InstrumentationRegistry.getTargetContext();
    private String[] fontArray =context .getResources().getStringArray(R.array.text_size);
    private String[] fontArrayValue = context.getResources().getStringArray(R.array.text_size_values);

    @Test
    public void testViewSettingsDisplayed() {
        onView(withText(R.string.pref_header_general)).check(matches(isDisplayed()));
        onView(withText(R.string.pref_header_general)).perform(click());
    }

    @Test
    public void testGeneralDisplayed() throws InterruptedException, IllegalAccessException, InstantiationException {

        testViewSettingsDisplayed();

        defaultPreferenceCheck();

        actionBarHomeClick();
        onView(withText(R.string.pref_header_general)).perform(click());
        onView(withText(R.string.pref_title_font_size)).check(matches(isDisplayed()));
        onView(withText(R.string.pref_title_font_size)).perform(click());

        closeSizeChoiceDialog(pressBack());

        for (String fontName : fontArray) {
            onView(withText(R.string.pref_title_font_size)).perform(click());
            onView(withText(fontName)).perform(click());
            onView(withText(fontName)).check(matches(isDisplayed()));
            onView(withText(R.string.pref_title_font_size)).check(matches(isDisplayed()));
        }
        actionBarHomeClick();
        onView(withText(R.string.pref_header_notifications)).perform(click());
        onView(withText(R.string.pref_title_new_message_notifications)).perform(click());
        actionBarHomeClick();
        onView(withText(R.string.pref_header_data_sync)).perform(click());
        actionBarHomeClick();
    }

    private void defaultPreferenceCheck() {
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(context);
        String s = p.getString("font_size", "");
        int defaultIndex = Arrays.asList(fontArrayValue).indexOf(s);
        onView(withText(R.string.pref_title_font_size)).check(matches(isDisplayed()));
        onView(withText(fontArray[defaultIndex])).check(matches(isDisplayed()));
    }

    private void actionBarHomeClick() {
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
    }

    private void closeSizeChoiceDialog(ViewAction viewAction) {
        onView(withText(R.string.pref_title_font_size)).perform(viewAction);
    }


}
