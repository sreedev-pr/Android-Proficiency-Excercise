package com.example.androidproficiencyexcercise.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidproficiencyexcercise.R
import com.example.androidproficiencyexcercise.adapters.FactsAdapter
import com.example.androidproficiencyexcercise.databinding.ActivityFactsBinding
import com.example.androidproficiencyexcercise.events.LoadingFinishedEvent
import com.example.androidproficiencyexcercise.model.Facts
import com.example.androidproficiencyexcercise.model.FactsResponseModel
import com.example.androidproficiencyexcercise.viewmodel.FactsViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

open class FactsActivity : AppCompatActivity() {
    private lateinit var activityFactsBinding: ActivityFactsBinding
    open lateinit var factsViewModel: FactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        setSwipeLayoutListener()
        callFactsApi()
    }

    override fun onStart() {
        super.onStart()
        // Register activity with eventbus
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        // Unregister activity with eventbus
        EventBus.getDefault().unregister(this)
    }

    /**
     * Subscriber method for listening to api call completion
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(loadingEvent: LoadingFinishedEvent) {
        toggleProgressBarVisibility(View.GONE)
        activityFactsBinding.swipeRefreshLayout.isRefreshing = false
    }

    /**
     * Function to set swipe to refresh listener
     */
    private fun setSwipeLayoutListener() {
        activityFactsBinding.swipeRefreshLayout.setOnRefreshListener {
            activityFactsBinding.swipeRefreshLayout.isRefreshing = false
            callFactsApi()
        }
    }


    /**
     * Function to set progressbar visibility
     * @param progressBarVisibility visibility value as integer
     */
    private fun toggleProgressBarVisibility(progressBarVisibility: Int) {
        activityFactsBinding.progressLoading.visibility =
            progressBarVisibility
    }

    /**
     * Function to call api implemented in viewmodel
     */
    private fun callFactsApi() {
        toggleProgressBarVisibility(View.VISIBLE)
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
        when (val factsAdapter = activityFactsBinding.listFacts.adapter) {
            null -> activityFactsBinding.listFacts.adapter = FactsAdapter(
                factsResponseModel!!.rows as MutableList<Facts>?,
                this
            )
            else -> {
                factsAdapter as FactsAdapter
                factsAdapter.updateFacts(factsResponseModel!!.rows as MutableList<Facts>?)
                factsAdapter.notifyDataSetChanged()
            }
        }
    }

    /**
     * Function to set actionbar title
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
