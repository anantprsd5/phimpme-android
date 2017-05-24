package vn.mbm.phimp.me.leafpic.activities;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import vn.mbm.phimp.me.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SplashScreenTest {

    @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule = new ActivityTestRule<>(SplashScreen.class);

    @Test
    public void splashScreenTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.grid_albums),
                        withParent(allOf(withId(R.id.rl_main_content),
                                withParent(withId(R.id.swipeRefreshLayout)))),
                        isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.grid_photos),
                        withParent(allOf(withId(R.id.rl_main_content),
                                withParent(withId(R.id.swipeRefreshLayout)))),
                        isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(3, click()));

    }

}
