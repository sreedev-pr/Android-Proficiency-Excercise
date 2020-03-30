package com.example.androidproficiencyexcercise.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.androidproficiencyexcercise.R
import com.example.androidproficiencyexcercise.databinding.ActivityFactsBinding
import com.example.androidproficiencyexcercise.viewmodel.FactsViewModel

class FactsActivity : AppCompatActivity() {
    private lateinit var activityFactsBinding: ActivityFactsBinding
    private lateinit var factsViewModel: FactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
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
