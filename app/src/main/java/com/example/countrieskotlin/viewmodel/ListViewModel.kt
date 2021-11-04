package com.example.countrieskotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrieskotlin.api.CountriesService
import com.example.countrieskotlin.model.Country

class ListViewModel : ViewModel() {
    private val countriesService : CountriesService = CountriesService()
    var countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean?>()
    val loading = MutableLiveData<Boolean>()


    fun refresh() {
        countriesService.fetchCountries(countries,countryLoadError,loading)
    }
}