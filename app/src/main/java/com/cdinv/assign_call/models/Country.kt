package com.cdinv.assign_call.models

import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class Country(
    @Json(name = "country")
    val country : String,

    @Json(name = "cities")
    val cities: List<String>

) : Serializable