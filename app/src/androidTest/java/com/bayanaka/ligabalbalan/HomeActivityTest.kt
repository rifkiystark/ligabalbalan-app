package com.bayanaka.ligabalbalan

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.bayanaka.ligabalbalan.modul.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testSearch() {
        onView(withId(R.id.search))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(EditText::class.java))
            .perform(typeText("madrid"), pressImeActionButton())
        Thread.sleep(7000)
        onView(isAssignableFrom(EditText::class.java))
            .perform(clearText())
        onView(isAssignableFrom(EditText::class.java))
            .perform(typeText("chelsea"), pressImeActionButton())
        Thread.sleep(7000)
        onView(withId(R.id.rvHomeMatch)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                7,
                click()
            )
        )
        pressBack()
    }
}