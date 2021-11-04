package com.example.countrieskotlin.api

import android.content.Context
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
    lateinit var api: CountriesApi
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var listView: ListViewModel

    init {
        DaggerApiComponent.create().inject(this)
    }

    private fun initList(){
        listView = ListViewModel()
    }
    fun initPref(context: Context) {
        initList()
        sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE)
    }

    private suspend fun getCountriesService(): Response<List<Country>> {
        return api.getCountries()
    }

    fun putSharedPref(name: String) {
        this.sharedPreferences.edit().putString("name", name).apply()
    }

    fun getSharedPref(): String? {
        return this.sharedPreferences.getString("name", null)
    }

    suspend fun getOneCountry(name: String): Country? {
        return api.getCountries().body()?.find {
            it.countryName == name
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }
    private fun onError(message: String) {
        Log.d("checkError", message)
        // Update the LiveData with the error message
        //listView.countryLoadError.value = true
        // Stop the [loading] indication
        //listView.loading.value = false
    }

    fun fetchCountries(
        countries: MutableLiveData<List<Country>>,
        countryLoadError: MutableLiveData<Boolean?>,
        loading: MutableLiveData<Boolean>
    ) {
        loading.value = true

        //Needs to run on main to go back later on UI thread (Main)
        CoroutineScope(Dispatchers.Main + exceptionHandler).launch {
            // Get the countries information from the API on the IO Dispatcher (api data for example)
            val response = withContext(Dispatchers.IO) {
                getCountriesService()
            }
            // Back to the UI thread

            // Check the response
            if (response.isSuccessful) {
                // Update the LiveData
                countryLoadError.value = false
                countries.value = response.body()!!
                Log.e("val", countries.value.toString())
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

