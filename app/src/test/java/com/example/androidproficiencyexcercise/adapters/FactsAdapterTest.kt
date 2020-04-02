package com.example.androidproficiencyexcercise.adapters

import android.content.Context
import com.example.androidproficiencyexcercise.model.Facts
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FactsAdapterTest {

    private lateinit var factsAdapter: FactsAdapter

    @Mock
    lateinit var mockContext: Context

    @Mock
    lateinit var mockFactList: MutableList<Facts>

    @Mock
    lateinit var mockUpdatedFactList: MutableList<Facts>


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        factsAdapter = FactsAdapter(mockFactList, mockContext)
    }

    @Test
    fun shouldClearExistingFactListOnUpdateFacts() {

        Mockito.`when`(mockFactList.isNullOrEmpty()).thenReturn(false)

        factsAdapter.updateFacts(mockUpdatedFactList)

        Mockito.verify(mockFactList).clear()
    }

    @Test
    fun shouldNotClearExistingFactListOnUpdateFacts() {

        Mockito.`when`(mockFactList.isNullOrEmpty()).thenReturn(true)

        factsAdapter.updateFacts(mockUpdatedFactList)

        Mockito.verify(mockFactList, Mockito.never()).clear()
    }

    @Test
    fun shouldUpdateFactListOnUpdateFacts() {
        factsAdapter.updateFacts(mockUpdatedFactList)

        assert(factsAdapter.factList == mockUpdatedFactList)
    }
}