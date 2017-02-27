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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;
@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchForKeyword {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void searchForKeyword() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.view_list_card_list), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.view_news_fullscreen_link_card_list), isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Find in page"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withId(R.id.search_src_text),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("flight"), closeSoftKeyboard());

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withId(R.id.search_src_text), withText("flight"),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete2.perform(pressImeActionButton());

        ViewInteraction textView = onView(
                allOf(withId(R.id.find_in_page_match), withText("1/10"),
                        childAtPosition(
                                allOf(withId(R.id.find_in_page_container),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.support.v7.widget.LinearLayoutCompat.class),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("1/10")));

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.action_mode_close_button), withContentDescription("Done"),
                        withParent(allOf(withId(R.id.action_mode_bar),
                                withParent(withId(R.id.action_bar_root)))),
                        isDisplayed()));
        appCompatImageView.perform(click());

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
