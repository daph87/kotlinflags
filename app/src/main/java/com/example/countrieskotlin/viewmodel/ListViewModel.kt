package com.example.countrieskotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrieskotlin.BuildConfig.DEBUG
import com.example.countrieskotlin.model.CountriesService
import com.example.countrieskotlin.model.Country
import io.reactivex.internal.util.HalfSerializer.onError
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

class ListViewModel : ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean?>()
    val loading = MutableLiveData<Boolean>()

    private val countriesService = CountriesService()

    // Coroutine Job instance to download the data from the API
    private var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    private fun onError(message: String) {
        Log.d("checkError", message)
        // Update the LiveData with the error message
        countryLoadError.value = true
        // Stop the [loading] indication
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        // Clear any active Coroutine jobs when not needed
        job?.cancel()
    }

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true

        //Needs to run on main to go back later on UI thread (Main)
        job = CoroutineScope(Dispatchers.Main + exceptionHandler).launch {
            // Get the countries information from the API on the IO Dispatcher (api data for example)
            val response = withContext(Dispatchers.IO) {
                countriesService.getCountriesService()
            }
            // Back to the UI thread

            // Check the response
            if (response.isSuccessful) {
                // Update the LiveData
                countryLoadError.value = false
                countries.value = response.body()
                // Stop the loading
                loading.value = false
            } else {
                Log.d("DebugRespFail", response.message())

                // When we have no response but an error
                // Show the error message
                onError("Error: ${response.message()}")
            }
        }

    }
}