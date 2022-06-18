package com.example.phonetracker;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
//import static java.util.EnumSet.allOf;

import android.view.View;

import androidx.test.espresso.ViewAssertion;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.phonetracker.ui.MainActivity;
import com.google.android.material.textfield.TextInputLayout;
import androidx.test.espresso.matcher.ViewMatchers;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import butterknife.BindView;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void validateEditText(){

        onView(
                allOf(
                        isDescendantOfA(withId(R.id.EditText)),
                        withClassName(endsWith("EditText"))
                )
        ).perform(
                typeText("Name")
        );



//        onView(allOf(isDescendantOfA(withId(R.id.EditText)).perform(typeText("Name"))
//                .check(matches(withText("Name")));
    }



//    @Test
//    public void validateEditText(){
//        onView(withId(R.id.EditText)).perform(typeText("Name"))
//                .check(matches(withText("Name")));
//    }

    private ViewAssertion matches(Matcher<View> Name) {
        return null;
    }


}
