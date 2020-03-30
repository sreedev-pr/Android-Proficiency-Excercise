package com.example.androidproficiencyexcercise.model

import androidx.annotation.Keep
import com.example.androidproficiencyexcercise.api.constant.APIConstants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
class Facts {
    @SerializedName(APIConstants.TITLE)
    @Expose
    var title: String? = null

    @SerializedName(APIConstants.DESCRIPTION)
    @Expose
    var description: String? = null

    @SerializedName(APIConstants.IMAGE_HREF)
    @Expose
    var imageHref: String? = null
}