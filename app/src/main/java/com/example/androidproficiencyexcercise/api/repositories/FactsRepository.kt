package com.example.androidproficiencyexcercise.api.repositories

import androidx.lifecycle.MutableLiveData
import com.example.androidproficiencyexcercise.api.service.APIService
import com.example.androidproficiencyexcercise.api.service.RetrofitService
import com.example.androidproficiencyexcercise.events.LoadingFinishedEvent
import com.example.androidproficiencyexcercise.model.FactsResponseModel
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Response

/**
 * Repository for initialising API service and calling api
 */
object FactsRepository {
    /**
     * Initialise api service
     */
    private var apiService: APIService = RetrofitService().createService()

    /**
     * Function to call api from api service
     * @return response for api
     */
    fun callApi(): MutableLiveData<FactsResponseModel>? {
        val factsMutableLiveData: MutableLiveData<FactsResponseModel> = MutableLiveData()
        apiService.getFacts().enqueue(object : retrofit2.Callback<FactsResponseModel> {
            override fun onFailure(call: Call<FactsResponseModel>, throwable: Throwable) {
                val factsResponseModel = FactsResponseModel()
                factsMutableLiveData.value = factsResponseModel
                EventBus.getDefault().post(LoadingFinishedEvent())
            }

            override fun onResponse(
                call: Call<FactsResponseModel>,
                response: Response<FactsResponseModel>
            ) {
                factsMutableLiveData.value = response.body()
                EventBus.getDefault().post(LoadingFinishedEvent())

            }

        })
        return factsMutableLiveData
    }
}