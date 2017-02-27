package org.wikipedia;


import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.startsWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddlistTest1 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addlistTest1() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.view_list_card_list), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.view_news_fullscreen_link_card_list), isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Add to reading list"), isDisplayed()));
        appCompatTextView.perform(click());
        try {
            ViewInteraction appCompatButton = onView(
                    allOf(withId(R.id.onboarding_button), withText("Got it"),
                            withParent(withId(R.id.onboarding_container)),
                            isDisplayed()));
            appCompatButton.perform(click());
        } catch (NoMatchingViewException e) {
            ViewInteraction linearLayout = onView(
                    Matchers.allOf(withId(R.id.create_button),
                            withParent(withId(R.id.lists_container)),
                            isDisplayed()));
            linearLayout.perform(click());
            SleepUtil.sleep(5);
        }
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        withParent(allOf(withId(R.id.buttonPanel),
                                withParent(withId(R.id.parentPanel)))),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction imageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(withId(R.id.page_toolbar)),
                        isDisplayed()));
        imageButton.perform(click());

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.view_news_fullscreen_link_card_list), isDisplayed()));
        recyclerView3.perform(actionOnItemAtPosition(1, click()));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.title), withText("Add to reading list"), isDisplayed()));
        appCompatTextView2.perform(click());
        SleepUtil.sleep(3);
        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.item_container), isDisplayed()));
        linearLayout.perform(click());
        SleepUtil.sleep(3);
        ViewInteraction imageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(withId(R.id.page_toolbar)),
                        isDisplayed()));
        imageButton2.perform(click());
        SleepUtil.sleep(3);
        ViewInteraction imageButton3 = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(withId(R.id.view_news_fullscreen_toolbar)),
                        isDisplayed()));
        imageButton3.perform(click());
        SleepUtil.sleep(3);
        ViewInteraction appCompatTextView3 = onView(
                allOf(withText("My lists"), isDisplayed()));
        appCompatTextView3.perform(click());
        SleepUtil.sleep(5);
        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.item_title), withText("My reading list"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_container),
                                        1),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("My reading list")));
        SleepUtil.sleep(5);
        ViewInteraction linearLayout2 = onView(
                Matchers.allOf(withId(R.id.item_container), isDisplayed()));
        linearLayout2.perform(click());
        SleepUtil.sleep(5);

        ViewInteraction textView1 = onView(
                allOf(withId(R.id.reading_list_count), withText(startsWith("2 articles")),
                        isDisplayed()));
        textView1.check(matches(withText(startsWith("2 articles"))));
        SleepUtil.sleep(5);
        ViewInteraction floatingActionButton = onView(
                Matchers.allOf(withId(R.id.button_edit), withContentDescription("Edit reading list details"), isDisplayed()));
        floatingActionButton.perform(click());
        SleepUtil.sleep(5);

        ViewInteraction appCompatTextView4 = onView(
                Matchers.allOf(withId(R.id.reading_list_delete_link), withText("Delete reading list")));
        appCompatTextView4.perform(scrollTo(), click());
        SleepUtil.sleep(5);
        ViewInteraction appCompatButton3 = onView(
                Matchers.allOf(withId(android.R.id.button1), withText("OK"),
                        withParent(Matchers.allOf(withId(R.id.buttonPanel),
                                withParent(withId(R.id.parentPanel)))),
                        isDisplayed()));
        appCompatButton3.perform(click());
        SleepUtil.sleep(5);
        ViewInteraction appCompatTextView7 = onView(
                Matchers.allOf(withText("Explore"), isDisplayed()));
        appCompatTextView7.perform(click());
        SleepUtil.sleep(5);


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
