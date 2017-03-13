package Storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;

import com.example.value.instantresume.MainActivity;
import com.example.value.instantresume.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by value on 3/9/17.
 */

public class URLStorageTest {
    private UiDevice mDevice;
    private UiObject pageView;
    final String TEST_URL = "test_url_1337";

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new
            ActivityTestRule<MainActivity>(MainActivity.class);

    @Rule
    public UiThreadTestRule uiThreadTestRule = new UiThreadTestRule();

    @Before
    public void saveURLToStorage()  throws Exception {
        SharedPreferences sharedPref = mMainActivityTestRule.getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putString( "url", "");
        boolean result = prefEditor.commit();
        assert(result == true);

        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        pageView = mDevice.findObject(new UiSelector());

        pageView.swipeRight(17);

        // enter url into edittext
        onView(withId(R.id.input_url)).perform(typeText(TEST_URL));

        // press save
        onView(withId(R.id.button_save)).perform(click());
    }

    @Test
    public void savedURL_showsUpOnMainScreen() {
        // check that saved url shows on mainscreen
        onView(withId(R.id.linkedin_url)).check(matches(withText(TEST_URL)));
    }

}