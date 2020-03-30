package com.example.androidproficiencyexcercise.api.service

import com.example.androidproficiencyexcercise.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class to build retrofit service
 */
class RetrofitService {
    /**
     * Initialising HTTP logging interceptor
     */
    private val client = OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().apply {
        level = when {
            BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
    }).build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(client)
        .build()

    /**
     * Function to create retrofit service
     */
    fun createService(): APIService {
        return retrofit.create(APIService::class.java)
    }
}