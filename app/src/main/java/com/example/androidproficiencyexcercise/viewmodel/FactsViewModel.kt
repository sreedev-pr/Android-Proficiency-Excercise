package com.example.androidproficiencyexcercise.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproficiencyexcercise.R
import com.example.androidproficiencyexcercise.api.repositories.FactsRepository
import com.example.androidproficiencyexcercise.model.FactsResponseModel
import com.example.androidproficiencyexcercise.utilities.StringUtils

/**
 * ViewModel class for FactsActivity
 */
open class FactsViewModel : ViewModel() {
    /**
     *  Live data listening to api call changes
     */
    var factsLiveData: MutableLiveData<FactsResponseModel> = MutableLiveData()

    /**
     * Function to call get facts api
     */
    fun callGetFactsApi() {
        factsLiveData = FactsRepository.callApi()!!
    }

    /**
     * Function to get visibility of 'no data found' text
     * @return visibility
     */
    fun getNoDataFoundVisibility(factsResponseModel: FactsResponseModel): Int {
        return if (factsResponseModel.rows.isNullOrEmpty()) {
            View.VISIBLE
        } else View.GONE
    }

    /**
     * Function to get visibility of listView
     * @return visibility
     */
    fun getListViewVisibility(factsResponseModel: FactsResponseModel): Int {
        return if (factsResponseModel.rows.isNullOrEmpty()) {
            View.GONE
        } else View.VISIBLE
    }

    /**
     * Function to get actionbar TITLE
     * @param title - TITLE returned from api call
     * @param context - context of activity
     * @return actionbar TITLE to be displayed
     */
    fun getActionBarTitle(title: String?, context: Context): String? {
        return if (StringUtils.isValidString(title)) {
            title
        } else context.getString(R.string.app_name)
    }
}