package com.example.androidproficiencyexcercise.utilities

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

class StringValidationTest {
    private val fakeString = "About Canada"
    private val emptyString = ""

    @Test
    fun stringValidationSuccess() {
        val isValidString = StringUtils.isValidString(fakeString)
        assertTrue(isValidString)
    }

    @Test
    fun stringValidationFailure() {
        val isValidString = StringUtils.isValidString(emptyString)
        assertFalse(isValidString)
    }
}