package com.cdinv.assign_call.api

import com.cdinv.assign_call.models.CountriesResponse
import com.cdinv.assign_call.models.Country
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {

    @GET("countries")
    suspend fun getCountries() : Response<CountriesResponse>
}