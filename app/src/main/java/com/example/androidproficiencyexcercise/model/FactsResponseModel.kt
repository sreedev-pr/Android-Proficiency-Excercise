package com.example.androidproficiencyexcercise.model

import androidx.annotation.Keep
import com.example.androidproficiencyexcercise.api.constant.APIConstants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Data model for mapping response received from facts api
 */
@Keep
open class FactsResponseModel {
    @SerializedName(APIConstants.TITLE)
    @Expose
    var title: String? = null

    @SerializedName(APIConstants.ROWS)
    @Expose
    open var rows: List<Facts>? = null
}