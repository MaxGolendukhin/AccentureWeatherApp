package com.golendukhin.accentureweatherapp

import android.content.Context
import android.view.KeyEvent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.golendukhin.accentureweatherapp.list.WeatherListFragmentAdapter
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test


class SwitchingCityTest {
    @get:Rule
    val mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    companion object {
        const val CITY = "Berlin"
        const val NEW_CITY = "Riga"
        lateinit var context: Context

        @JvmStatic
        @BeforeClass
        fun setContext() {
            context = InstrumentationRegistry.getInstrumentation().targetContext
        }
    }

    @Before
    fun setPreferencesValue() {
        val pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(PREFERENCES_CITY_KEY, SharedPreferencesTest.CITY).apply()
    }

    @Test
    fun testSwitchingCity_newCityInDetails() {
        onView(withId(R.id.preferences_menu_item)).perform(click())
        for (i in 0..CITY.length)
            onView(withId(R.id.city_edit_text)).perform(pressKey(KeyEvent.KEYCODE_DEL))

        onView(withId(R.id.city_edit_text)).perform(typeText(NEW_CITY))
        onView(withId(R.id.submit_button)).perform(click())
        onView(withId(R.id.save_menu_item)).perform(click())
        onView(withId(R.id.weather_list))
            .perform(actionOnItemAtPosition<WeatherListFragmentAdapter.ViewHolder>(
                0, MyViewAction.clickChildViewWithId(R.id.more_details_textView)))
        onView(withId(R.id.city)).check(
            ViewAssertions.matches(withText(NEW_CITY)))
    }

    object MyViewAction {
        fun clickChildViewWithId(id: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? {
                    return null
                }

                override fun getDescription(): String {
                    return "Click on a child view with specified id."
                }

                override fun perform(uiController: UiController, view: View) {
                    val v = view.findViewById<View>(id)
                    v.performClick()
                }
            }
        }
    }
}