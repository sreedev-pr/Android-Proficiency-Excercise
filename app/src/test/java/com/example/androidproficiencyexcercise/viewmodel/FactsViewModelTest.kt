package com.example.androidproficiencyexcercise.viewmodel

import android.content.Context
import android.view.View
import com.example.androidproficiencyexcercise.R
import com.example.androidproficiencyexcercise.model.Facts
import com.example.androidproficiencyexcercise.model.FactsResponseModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FactsViewModelTest {
    @Mock
    lateinit var mockFactsResponseModel: FactsResponseModel

    @Mock
    lateinit var mockListFacts: List<Facts>

    @Mock
    lateinit var mockContext: Context

    lateinit var factsViewModel: FactsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        factsViewModel = FactsViewModel()
    }

    @Test
    fun shouldReturnVisibleIfResponseModelHasData() {
        Mockito.`when`(mockFactsResponseModel.rows).thenReturn(mockListFacts)
        Mockito.`when`(mockListFacts.isNullOrEmpty()).thenReturn(false)

        val listViewVisibility = factsViewModel.getListViewVisibility(mockFactsResponseModel)

        assert(listViewVisibility == View.VISIBLE)
    }

    @Test
    fun shouldReturnGoneIfResponseModelDoesNotHaveData() {
        Mockito.`when`(mockFactsResponseModel.rows).thenReturn(mockListFacts)
        Mockito.`when`(mockListFacts.isNullOrEmpty()).thenReturn(true)

        val listViewVisibility = factsViewModel.getListViewVisibility(mockFactsResponseModel)

        assert(listViewVisibility == View.GONE)
    }

    @Test
    fun shouldReturnAppNameIfTitleIsInvalid() {
        val appName = "testAppName"
        val invalidTitle = ""
        Mockito.`when`(mockContext.getString(R.string.app_name)).thenReturn(appName)

        val actionBarTitle = factsViewModel.getActionBarTitle(invalidTitle, mockContext)

        assert(actionBarTitle == appName)
    }

    @Test
    fun shouldReturnTitleIfStringIsValid() {
        val appName = "testAppName"
        val validTitle = "validTitle"
        Mockito.`when`(mockContext.getString(R.string.app_name)).thenReturn(appName)

        val actionBarTitle = factsViewModel.getActionBarTitle(validTitle, mockContext)

        assert(actionBarTitle == validTitle)
    }

}