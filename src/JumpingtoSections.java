package org.wikipedia;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;
@LargeTest
@RunWith(AndroidJUnit4.class)
public class JumpingtoSections {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void jumpingtoSections() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.view_list_card_list), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.view_news_fullscreen_link_card_list), isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatTextView = onView(
                allOf(withText("b#5"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        withId(R.id.page_toc_list),
                        1),
                        isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withText("b#5"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction linearLayout2 = onView(
                allOf(childAtPosition(
                        withId(R.id.page_toc_list),
                        2),
                        isDisplayed()));
        linearLayout2.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withText("b#5"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction linearLayout3 = onView(
                allOf(childAtPosition(
                        withId(R.id.page_toc_list),
                        3),
                        isDisplayed()));
        linearLayout3.perform(click());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withText("b#5"), isDisplayed()));
        appCompatTextView4.perform(click());

        ViewInteraction linearLayout4 = onView(
                allOf(childAtPosition(
                        withId(R.id.page_toc_list),
                        4),
                        isDisplayed()));
        linearLayout4.perform(click());

        ViewInteraction appCompatTextView5 = onView(
                allOf(withText("b#5"), isDisplayed()));
        appCompatTextView5.perform(click());

        ViewInteraction linearLayout5 = onView(
                allOf(childAtPosition(
                        withId(R.id.page_toc_list),
                        5),
                        isDisplayed()));
        linearLayout5.perform(click());

        ViewInteraction appCompatTextView6 = onView(
                allOf(withText("b#5"), isDisplayed()));
        appCompatTextView6.perform(click());

        ViewInteraction appCompatTextView7 = onView(
                allOf(withText("Pakistan International Airlines Flight 661"),
                        childAtPosition(
                                withId(R.id.page_toc_list),
                                0),
                        isDisplayed()));
        appCompatTextView7.perform(click());

        ViewInteraction imageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(withId(R.id.page_toolbar)),
                        isDisplayed()));
        imageButton.perform(click());

        ViewInteraction imageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(withId(R.id.view_news_fullscreen_toolbar)),
                        isDisplayed()));
        imageButton2.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
