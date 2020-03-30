package com.example.androidproficiencyexcercise.api.service

import com.example.androidproficiencyexcercise.api.constant.APIConstants
import com.example.androidproficiencyexcercise.model.FactsResponseModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Interface to manage api calls
 */
interface APIService {
    @GET(APIConstants.FACTS_URL)
    fun getFacts(): Call<FactsResponseModel>
}