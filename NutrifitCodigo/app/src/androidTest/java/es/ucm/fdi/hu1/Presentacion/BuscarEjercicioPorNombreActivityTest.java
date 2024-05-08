package es.ucm.fdi.hu1.Presentacion;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.ucm.fdi.hu1.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BuscarEjercicioPorNombreActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void buscarEjercicioPorNombreActivityTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.addButtonEjsFr), withContentDescription("Crear Ejercicio"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawer_layout),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.EjerTitle),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Ejercicio pecho"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinnerCrearEjGrupoMusc),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinnerCrearEjTipoEj),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView2.perform(click());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.saveEjer), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.addButtonEjsFr), withContentDescription("Crear Ejercicio"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawer_layout),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.EjerTitle),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("Ejercicio biceps"), closeSoftKeyboard());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.saveEjer), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withId(com.google.android.material.R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(com.google.android.material.R.id.search_plate),
                                        childAtPosition(
                                                withId(com.google.android.material.R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(click());

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withId(com.google.android.material.R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(com.google.android.material.R.id.search_plate),
                                        childAtPosition(
                                                withId(com.google.android.material.R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete2.perform(replaceText("Ejercicio"), closeSoftKeyboard());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(com.google.android.material.R.id.search_close_btn), withContentDescription("Clear query"),
                        childAtPosition(
                                allOf(withId(com.google.android.material.R.id.search_plate),
                                        childAtPosition(
                                                withId(com.google.android.material.R.id.search_edit_frame),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete3 = onView(
                allOf(withId(com.google.android.material.R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(com.google.android.material.R.id.search_plate),
                                        childAtPosition(
                                                withId(com.google.android.material.R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete3.perform(click());

        ViewInteraction searchAutoComplete4 = onView(
                allOf(withId(com.google.android.material.R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(com.google.android.material.R.id.search_plate),
                                        childAtPosition(
                                                withId(com.google.android.material.R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete4.perform(replaceText("bice"), closeSoftKeyboard());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(com.google.android.material.R.id.search_close_btn), withContentDescription("Clear query"),
                        childAtPosition(
                                allOf(withId(com.google.android.material.R.id.search_plate),
                                        childAtPosition(
                                                withId(com.google.android.material.R.id.search_edit_frame),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction searchAutoComplete5 = onView(
                allOf(withId(com.google.android.material.R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(com.google.android.material.R.id.search_plate),
                                        childAtPosition(
                                                withId(com.google.android.material.R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete5.perform(click());

        ViewInteraction searchAutoComplete6 = onView(
                allOf(withId(com.google.android.material.R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(com.google.android.material.R.id.search_plate),
                                        childAtPosition(
                                                withId(com.google.android.material.R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete6.perform(replaceText("no hay"), closeSoftKeyboard());

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(com.google.android.material.R.id.search_close_btn), withContentDescription("Clear query"),
                        childAtPosition(
                                allOf(withId(com.google.android.material.R.id.search_plate),
                                        childAtPosition(
                                                withId(com.google.android.material.R.id.search_edit_frame),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageView3.perform(click());
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
