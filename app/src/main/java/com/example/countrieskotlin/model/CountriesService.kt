package com.example.countrieskotlin.model

import com.example.countrieskotlin.di.DaggerApiComponent
import retrofit2.Response
import javax.inject.Inject


class CountriesService {

@Inject
lateinit var api :CountriesApi

init{
    DaggerApiComponent.create().inject(this)
}
  suspend fun getCountriesService(): Response<List<Country>>{
      return api.getCountries()
  }
}

