package com.ajspeller.justjava;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class JustJavaTest {

    @Rule
    public ActivityTestRule<JustJava> mActivityTestRule = new ActivityTestRule<>(JustJava.class);

    @Test
    public void DoesTheJustJavaActivityHaveTheSizeLabel() {
        //onView(allOf(withId(R.id.coffeeSizeLabel), withText("Size")));
        onView(withId(R.id.coffeeSizeLabel)).check(matches(withText("Size")));
    }

    @Test
    public void CheckForTheExistenceOfTheRadioGroup() {
        onView(withId(R.id.coffeeSizeGroup));
    }

    @Test
    public void CheckForTheExistenceOfSmallSize() {
        onView(withId(R.id.coffeeSmall)).check(matches((withText("Small"))));
    }

    @Test
    public void CheckForTheExistenceOfLargeSize() {
        onView(withId(R.id.coffeeLarge)).check(matches(withText("Large")));
    }

    @Test
    public void SmallShouldBeTheDefaultSize() {
        onView(withId(R.id.coffeeSmall)).check(matches(isChecked()));
    }

    @Test
    public void LargeShouldNotBeTheDefaultSize() {
        onView(withId(R.id.coffeeLarge)).check(matches(isNotChecked()));
    }

    @Test
    public void VerifyThatPricePerCupLabelExists() {
        onView(withId(R.id.pricePerCupLabel)).check(matches(withText("Price Per Cup")));
    }

    @Test
    public void VerifyThatPricePerCupDisplayExists() {
        onView(withId(R.id.pricePerCupDisplay));
    }

    @Test
    public void VerifyThatPricePerCupDisplayDefaultsToTwoDollarsPerCup() {
        onView(withId(R.id.pricePerCupDisplay)).check(matches(withText("$2.00")));
    }

    @Test
    public void VerifyThatPricePerCupDisplayDefaultsToFourDollarsPerCupWhenLargeIsSelected() {
        onView(withId(R.id.coffeeLarge)).perform(click());
        onView(withId(R.id.pricePerCupDisplay)).check(matches(withText("$4.00")));
    }

    @Test
    public void VerifyThatPricePerCupDisplayChangesToTwoDollarsPerCupWhenSmallIsSelected() {
        onView(withId(R.id.coffeeSmall)).perform(click());
        onView(withId(R.id.pricePerCupDisplay)).check(matches(withText("$2.00")));
    }

    @Test
    public void IsTheToppingsLabelPresent() {
        onView(withId(R.id.toppingsLabel)).check(matches(withText("Toppings")));
    }

    @Test
    public void IsWhippedCreamATopping() {
        onView(withId(R.id.whipped_cream_checkbox)).check(matches(withText("Whipped Cream")));
    }

    @Test
    public void WhippedCreamDefaultSettingIsFalse() {
        onView(withId(R.id.whipped_cream_checkbox)).check(matches(isNotChecked()));
    }

    @Test
    public void WhippedCreamSetToTrue() {
        onView(withId(R.id.whipped_cream_checkbox)).perform(click());
        onView(withId(R.id.whipped_cream_checkbox)).check(matches(isChecked()));
    }

    @Test
    public void WhippedCreamSetToFalse() {
        onView(withId(R.id.whipped_cream_checkbox)).perform(click());
        onView(withId(R.id.whipped_cream_checkbox)).perform(click());
        onView(withId(R.id.whipped_cream_checkbox)).check(matches(isNotChecked()));
    }
}
