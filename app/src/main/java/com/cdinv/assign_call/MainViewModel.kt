package com.cdinv.assign_call

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cdinv.assign_call.api.RetrofitInstance
import com.cdinv.assign_call.models.Country
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel()
{
    private val _countriesData : MutableLiveData<List<Country>> = MutableLiveData()

    val countriesData : LiveData<List<Country>> get() = _countriesData

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage : LiveData<String?>
        get() = _errorMessage

//    private var currentPage  = 1

    fun getCountries() {
        viewModelScope.launch {
//            Log.i(TAG,"Query with page $currentPage")
            _errorMessage.value = null
            _isLoading.value = true
            try {
                val countriesResponse = RetrofitInstance.api.getCountries()
                Log.i(TAG,"Fetched response: $countriesResponse")
                if (countriesResponse.isSuccessful) {
                    val countries = countriesResponse.body()
                    if (countries != null) {
                        _countriesData.postValue(countries.data)
                    } else {
                        _errorMessage.postValue("Error: Empty response body")
                    }
                } else {
                    _errorMessage.postValue("Error: ${countriesResponse.code()} ${countriesResponse.message()}")
                }
            } catch (e : Exception) {
                _errorMessage.value = e.message
                Log.i(TAG, "Exception $e")
            } finally {
                _isLoading.value = false
            }
        }
    }
}