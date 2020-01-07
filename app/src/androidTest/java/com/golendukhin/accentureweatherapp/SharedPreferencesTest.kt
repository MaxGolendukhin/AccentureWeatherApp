package com.golendukhin.accentureweatherapp

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test

class SharedPreferencesTest {
    @get:Rule
    val mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    companion object {
        const val CITY = "Berlin"
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
        editor.putString(PREFERENCES_CITY_KEY, CITY).apply()
    }

    @Test
    fun useAppContext() {
        onView(withId(R.id.preferences_menu_item)).perform(click())
        onView(withId(R.id.city_edit_text)).check(matches(withText(CITY)))
    }
}