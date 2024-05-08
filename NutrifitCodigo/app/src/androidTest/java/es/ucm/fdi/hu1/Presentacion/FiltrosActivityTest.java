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
public class FiltrosActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void filtrosActivityTest() {
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

        ViewInteraction appCompatSpinner3 = onView(
                allOf(withId(R.id.spinnerCrearEjGrupoMusc),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1),
                        isDisplayed()));
        appCompatSpinner3.perform(click());

        DataInteraction appCompatCheckedTextView3 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5);
        appCompatCheckedTextView3.perform(click());

        ViewInteraction appCompatSpinner4 = onView(
                allOf(withId(R.id.spinnerCrearEjTipoEj),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1),
                        isDisplayed()));
        appCompatSpinner4.perform(click());

        DataInteraction appCompatCheckedTextView4 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView4.perform(click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.saveEjer), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction floatingActionButton3 = onView(
                allOf(withId(R.id.addButtonEjsFr), withContentDescription("Crear Ejercicio"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawer_layout),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton3.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.EjerTitle),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("Prueba correr"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner5 = onView(
                allOf(withId(R.id.spinnerCrearEjGrupoMusc),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1),
                        isDisplayed()));
        appCompatSpinner5.perform(click());

        DataInteraction appCompatCheckedTextView5 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        appCompatCheckedTextView5.perform(click());

        ViewInteraction appCompatSpinner6 = onView(
                allOf(withId(R.id.spinnerCrearEjTipoEj),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1),
                        isDisplayed()));
        appCompatSpinner6.perform(click());

        DataInteraction appCompatCheckedTextView6 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView6.perform(click());

        ViewInteraction actionMenuItemView3 = onView(
                allOf(withId(R.id.saveEjer), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView3.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.filterEjButton), withText("Filtros"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction chip = onView(
                allOf(withText("NINGUNO"),
                        childAtPosition(
                                allOf(withId(R.id.EjMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                0)));
        chip.perform(scrollTo(), click());

        ViewInteraction chip2 = onView(
                allOf(withText("FUERZA"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip2.perform(scrollTo(), click());

        ViewInteraction chip3 = onView(
                allOf(withText("FUERZA"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip3.perform(scrollTo(), click());

        ViewInteraction chip4 = onView(
                allOf(withText("CARDIO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                1)));
        chip4.perform(scrollTo(), click());

        ViewInteraction chip5 = onView(
                allOf(withText("CARDIO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                1)));
        chip5.perform(scrollTo(), click());

        ViewInteraction chip6 = onView(
                allOf(withText("PECHO"),
                        childAtPosition(
                                allOf(withId(R.id.EjMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip6.perform(scrollTo(), click());

        ViewInteraction chip7 = onView(
                allOf(withText("CARDIO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                1)));
        chip7.perform(scrollTo(), click());

        ViewInteraction chip8 = onView(
                allOf(withText("CARDIO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                1)));
        chip8.perform(scrollTo(), click());

        ViewInteraction chip9 = onView(
                allOf(withText("NINGUNO"),
                        childAtPosition(
                                allOf(withId(R.id.EjMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                0)));
        chip9.perform(scrollTo(), click());

        ViewInteraction chip10 = onView(
                allOf(withText("CARDIO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                1)));
        chip10.perform(scrollTo(), click());

        ViewInteraction chip11 = onView(
                allOf(withText("CARDIO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                1)));
        chip11.perform(scrollTo(), click());

        ViewInteraction chip12 = onView(
                allOf(withText("FUERZA"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip12.perform(scrollTo(), click());

        ViewInteraction chip13 = onView(
                allOf(withText("CARDIO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                1)));
        chip13.perform(scrollTo(), click());

        ViewInteraction chip14 = onView(
                allOf(withText("NINGUNO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                0)));
        chip14.perform(scrollTo(), click());

        ViewInteraction chip15 = onView(
                allOf(withText("CARDIO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                1)));
        chip15.perform(scrollTo(), click());

        ViewInteraction chip16 = onView(
                allOf(withText("FUERZA"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip16.perform(scrollTo(), click());

        ViewInteraction chip17 = onView(
                allOf(withText("NINGUNO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                0)));
        chip17.perform(scrollTo(), click());

        ViewInteraction chip18 = onView(
                allOf(withText("PECHO"),
                        childAtPosition(
                                allOf(withId(R.id.EjMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip18.perform(scrollTo(), click());

        ViewInteraction chip19 = onView(
                allOf(withText("BICEPS"),
                        childAtPosition(
                                allOf(withId(R.id.EjMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                5)));
        chip19.perform(scrollTo(), click());

        ViewInteraction chip20 = onView(
                allOf(withText("CARDIO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                1)));
        chip20.perform(scrollTo(), click());

        ViewInteraction chip21 = onView(
                allOf(withText("CARDIO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                1)));
        chip21.perform(scrollTo(), click());

        ViewInteraction chip22 = onView(
                allOf(withText("FUERZA"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip22.perform(scrollTo(), click());

        ViewInteraction chip23 = onView(
                allOf(withText("PECHO"),
                        childAtPosition(
                                allOf(withId(R.id.EjMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip23.perform(scrollTo(), click());

        ViewInteraction chip24 = onView(
                allOf(withText("CARDIO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                1)));
        chip24.perform(scrollTo(), click());

        ViewInteraction chip25 = onView(
                allOf(withText("NINGUNO"),
                        childAtPosition(
                                allOf(withId(R.id.EjMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                0)));
        chip25.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.filterEjButton), withText("Filtros"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction floatingActionButton4 = onView(
                allOf(withId(R.id.addButtonEjsFr), withContentDescription("Crear Ejercicio"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawer_layout),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton4.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.EjerTitle),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("prueba"), closeSoftKeyboard());

        ViewInteraction actionMenuItemView4 = onView(
                allOf(withId(R.id.saveEjer), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView4.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.filterEjButton), withText("Filtros"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction chip26 = onView(
                allOf(withText("CARDIO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                1)));
        chip26.perform(scrollTo(), click());

        ViewInteraction chip27 = onView(
                allOf(withText("FUERZA"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip27.perform(scrollTo(), click());

        ViewInteraction chip28 = onView(
                allOf(withText("PECHO"),
                        childAtPosition(
                                allOf(withId(R.id.EjMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip28.perform(scrollTo(), click());

        ViewInteraction chip29 = onView(
                allOf(withText("NINGUNO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                0)));
        chip29.perform(scrollTo(), click());

        ViewInteraction chip30 = onView(
                allOf(withText("PECHO"),
                        childAtPosition(
                                allOf(withId(R.id.EjMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip30.perform(scrollTo(), click());

        ViewInteraction chip31 = onView(
                allOf(withText("FUERZA"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip31.perform(scrollTo(), click());

        ViewInteraction chip32 = onView(
                allOf(withText("CARDIO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                1)));
        chip32.perform(scrollTo(), click());

        ViewInteraction chip33 = onView(
                allOf(withText("NINGUNO"),
                        childAtPosition(
                                allOf(withId(R.id.EjMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                0)));
        chip33.perform(scrollTo(), click());

        ViewInteraction chip34 = onView(
                allOf(withText("NINGUNO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                0)));
        chip34.perform(scrollTo(), click());

        ViewInteraction chip35 = onView(
                allOf(withText("FUERZA"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip35.perform(scrollTo(), click());

        ViewInteraction chip36 = onView(
                allOf(withText("PECHO"),
                        childAtPosition(
                                allOf(withId(R.id.EjMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                2)));
        chip36.perform(scrollTo(), click());

        ViewInteraction chip37 = onView(
                allOf(withText("CARDIO"),
                        childAtPosition(
                                allOf(withId(R.id.EjTipoEjChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                1)));
        chip37.perform(scrollTo(), click());

        ViewInteraction chip38 = onView(
                allOf(withText("BICEPS"),
                        childAtPosition(
                                allOf(withId(R.id.EjMuscGroupChipGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.HorizontalScrollView")),
                                                0)),
                                5)));
        chip38.perform(scrollTo(), click());
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
