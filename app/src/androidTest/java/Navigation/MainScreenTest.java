package Navigation;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;

import com.example.value.instantresume.MainActivity;
import com.example.value.instantresume.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class MainScreenTest {
    private UiDevice mDevice;
    private UiObject pageView;

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new
            ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void NavigateToMainScreen() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        pageView = mDevice.findObject(new UiSelector());
    }

    @Test
    public void clickSetupButton_goesToSetUpScreen() throws Exception {
        String MENU_OPTION_TEXT = getInstrumentation().getTargetContext().getResources().getString(R.string.option_text);

        // Go to Main Screen
        pageView.swipeLeft(17);

        Thread.sleep(2000);

        //click on the sign up button
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(MENU_OPTION_TEXT)).perform(click());

        //check if the setup screen is displayed by asserting that the first name edittext is displayed
        onView(withId(R.id.input_url)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_setup
        )), isDisplayed())));
    }

}