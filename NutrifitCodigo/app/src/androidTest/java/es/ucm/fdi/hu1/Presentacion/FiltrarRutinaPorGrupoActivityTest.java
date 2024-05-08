package es.ucm.fdi.hu1.Presentacion;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
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
public class FiltrarRutinaPorGrupoActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void filtrarRutinaPorGrupoActivityTest() {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.routineButton), withContentDescription("Rutinas"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNav),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.addButton), withContentDescription("Crear Ejercicio"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.main),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.RutinaTitle),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Prueba rutina pecho"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinnerCrearRutinaGrupoMusc),
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

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.saveRutina), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.addButton), withContentDescription("Crear Ejercicio"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.main),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.RutinaTitle),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("Prueba rutina biceps"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinnerCrearRutinaGrupoMusc),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5);
        appCompatCheckedTextView2.perform(click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.saveRutina), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction floatingActionButton3 = onView(
                allOf(withId(R.id.addButton), withContentDescription("Crear Ejercicio"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.main),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton3.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.RutinaTitle),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("Prueba rutina"), closeSoftKeyboard());

        ViewInteraction actionMenuItemView3 = onView(
                allOf(withId(R.id.saveRutina), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView3.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.filterRutButton), withText("Filtros"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction chip = onView(
                allOf(withText("PECHO"),
                        childAtPosition(
                                allOf(withId(R.id.RutsMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip.perform(scrollTo(), click());

        ViewInteraction chip2 = onView(
                allOf(withText("HOMBRO"),
                        childAtPosition(
                                allOf(withId(R.id.RutsMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                3)));
        chip2.perform(scrollTo(), click());

        ViewInteraction chip3 = onView(
                allOf(withText("PECHO"),
                        childAtPosition(
                                allOf(withId(R.id.RutsMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip3.perform(scrollTo(), click());

        ViewInteraction chip4 = onView(
                allOf(withText("HOMBRO"),
                        childAtPosition(
                                allOf(withId(R.id.RutsMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                3)));
        chip4.perform(scrollTo(), click());

        ViewInteraction chip5 = onView(
                allOf(withText("NINGUNO"),
                        childAtPosition(
                                allOf(withId(R.id.RutsMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                0)));
        chip5.perform(scrollTo(), click());

        ViewInteraction chip6 = onView(
                allOf(withText("NINGUNO"),
                        childAtPosition(
                                allOf(withId(R.id.RutsMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                0)));
        chip6.perform(scrollTo(), click());

        ViewInteraction chip7 = onView(
                allOf(withText("BICEPS"),
                        childAtPosition(
                                allOf(withId(R.id.RutsMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                5)));
        chip7.perform(scrollTo(), click());

        ViewInteraction chip8 = onView(
                allOf(withText("PECHO"),
                        childAtPosition(
                                allOf(withId(R.id.RutsMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip8.perform(scrollTo(), click());

        ViewInteraction chip9 = onView(
                allOf(withText("NINGUNO"),
                        childAtPosition(
                                allOf(withId(R.id.RutsMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                0)));
        chip9.perform(scrollTo(), click());

        ViewInteraction chip10 = onView(
                allOf(withText("NINGUNO"),
                        childAtPosition(
                                allOf(withId(R.id.RutsMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                0)));
        chip10.perform(scrollTo(), click());

        ViewInteraction chip11 = onView(
                allOf(withText("PECHO"),
                        childAtPosition(
                                allOf(withId(R.id.RutsMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip11.perform(scrollTo(), click());

        ViewInteraction chip12 = onView(
                allOf(withText("BICEPS"),
                        childAtPosition(
                                allOf(withId(R.id.RutsMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                5)));
        chip12.perform(scrollTo(), click());
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
