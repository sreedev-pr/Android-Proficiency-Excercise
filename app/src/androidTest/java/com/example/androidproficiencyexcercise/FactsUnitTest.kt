package com.example.androidproficiencyexcercise

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.androidproficiencyexcercise.view.FactsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class FactsUnitTest {
    @get:Rule
    val factsActivityRule: ActivityTestRule<FactsActivity> =
        ActivityTestRule(FactsActivity::class.java)

    @Test
    fun listView_isDisplayed() {
        onView(ViewMatchers.withId(R.id.list_facts))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun noDataFound_isDisplayed() {
        onView(ViewMatchers.withId(R.id.text_no_data_found))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}