package com.example.countrieskotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrieskotlin.api.CountriesService
import com.example.countrieskotlin.model.Country
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

class ListViewModel : ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean?>()
    val loading = MutableLiveData<Boolean>()

    private val countriesService : CountriesService = CountriesService()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }
     fun onError(message: String) {
        Log.d("checkError", message)
        // Update the LiveData with the error message
        countryLoadError.value = true
        // Stop the [loading] indication
        loading.value = false
    }

    fun refresh() {
        countriesService.fetchCountries(countries, countryLoadError, loading, exceptionHandler)
    }

}