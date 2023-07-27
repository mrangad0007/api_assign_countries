package com.cdinv.assign_call.models

import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class CountriesResponse(
    @Json(name = "data")
    val data: List<Country>
) : Serializable