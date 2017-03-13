package Navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.test.rule.ActivityTestRule;

import com.example.value.instantresume.MainActivity;
import com.example.value.instantresume.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by value on 3/12/17.
 */

public class ScreenInitializationTest {
    final String TEST_URL = "test_url_1337";

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new
            ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void ReopenScreenWithSavedData_GoesToMainScreen() {
        SharedPreferences sharedPref = mMainActivityTestRule.getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putString( "url", TEST_URL);
        boolean result = prefEditor.commit();
        assert(result == true);

        Activity mainActivity = mMainActivityTestRule.getActivity();

        Intent intent = mainActivity.getIntent();
        mainActivity.finish();

        mainActivity.startActivity(intent);
        // assert that main screen is displayed
        onView(withId(R.id.linkedin_url)).check(matches(withText(TEST_URL)));
    }

    @Test
    public void ReopenScreenWithoutSavedData_GoesToSetupScreen() {
        SharedPreferences sharedPref = mMainActivityTestRule.getActivity().getPreferences(Context.MODE_PRIVATE);
        boolean result = sharedPref.edit().clear().commit();
        assert(result == true);

        Activity mainActivity = mMainActivityTestRule.getActivity();

        Intent intent = mainActivity.getIntent();
        mainActivity.finish();

        mainActivity.startActivity(intent);
        // assert that the setup screen is displayed by asserting that the first name edittext is displayed
        onView(withId(R.id.input_url)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_setup
        )), isDisplayed())));
    }
}
