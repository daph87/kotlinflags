package com.example.countrieskotlin.api

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.countrieskotlin.di.DaggerApiComponent
import com.example.countrieskotlin.model.Country
import com.example.countrieskotlin.viewmodel.ListViewModel
import kotlinx.coroutines.*
import retrofit2.Response
import javax.inject.Inject


class CountriesService {

@Inject
lateinit var api : CountriesApi
private lateinit var sharedPreferences: SharedPreferences
private lateinit var listView : ListViewModel

init{
    DaggerApiComponent.create().inject(this)
}
  suspend fun getCountriesService(): Response<List<Country>>{
      return api.getCountries()
  }

    fun putSharedPref(name : String){
        this.sharedPreferences.edit().putString("name",name).apply()
    }
    fun getSharedPref() : String? {
        return this.sharedPreferences.getString("name",null)
    }

    fun fetchCountries(
        countries: MutableLiveData<List<Country>>,
        countryLoadError: MutableLiveData<Boolean?>,
        loading: MutableLiveData<Boolean>,
        exceptionHandler: CoroutineExceptionHandler,
    ) {
        loading.value = true

        //Needs to run on main to go back later on UI thread (Main)
        CoroutineScope(Dispatchers.Main + exceptionHandler).launch {
            // Get the countries information from the API on the IO Dispatcher (api data for example)
            val response = withContext(Dispatchers.IO) {
                api.getCountries()
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
              listView.onError("Error: ${response.message()}")
            }
        }

    }
}

