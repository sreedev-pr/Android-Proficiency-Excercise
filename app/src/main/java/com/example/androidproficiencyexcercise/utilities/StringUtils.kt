package com.example.androidproficiencyexcercise.utilities

/**
 * Class to manipulate string
 */
object StringUtils {
    fun isValidString(string: String?): Boolean {
        return !string.isNullOrEmpty()
    }
}