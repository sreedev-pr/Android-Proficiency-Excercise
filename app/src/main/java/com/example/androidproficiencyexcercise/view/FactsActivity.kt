package com.example.androidproficiencyexcercise.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidproficiencyexcercise.R
import com.example.androidproficiencyexcercise.databinding.ActivityFactsBinding
import com.example.androidproficiencyexcercise.model.FactsResponseModel
import com.example.androidproficiencyexcercise.viewmodel.FactsViewModel

class FactsActivity : AppCompatActivity() {
    private lateinit var activityFactsBinding: ActivityFactsBinding
    private lateinit var factsViewModel: FactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        callFactsApi()
    }

    /**
     * Function to call api implemented in viewmodel
     */
    private fun callFactsApi() {
        activityFactsBinding.factsViewModel!!.callGetFactsApi()
        subscribeToFactsApiCall()
    }

    /**
     * Subscribe to api call for populating list view
     */
    private fun subscribeToFactsApiCall() {
        factsViewModel.factsLiveData.observe(this,
            Observer<FactsResponseModel> { factsResponseModel ->
                activityFactsBinding.textNoDataFound.visibility =
                    factsViewModel.getNoDataFoundVisibility(factsResponseModel)
                activityFactsBinding.listFacts.visibility =
                    factsViewModel.getListViewVisibility(factsResponseModel)
                setUpActionBar(factsResponseModel!!.title)
                setUpFactsListView(factsResponseModel)
            })
    }

    /**
     * Function to set adapter for list view
     * @param factsResponseModel data to be passed to adapter
     */
    private fun setUpFactsListView(factsResponseModel: FactsResponseModel?) {
    }

    /**
     * Function to set actionbar TITLE
     */
    private fun setUpActionBar(title: String?) {
        val actionBar = supportActionBar
        actionBar!!.title = factsViewModel.getActionBarTitle(title, this)
    }

    /**
     * Initialise data binding
     */
    private fun initDataBinding() {
        activityFactsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_facts)
        factsViewModel = ViewModelProvider(this).get(FactsViewModel::class.java)
        activityFactsBinding.factsViewModel = factsViewModel
    }
}
