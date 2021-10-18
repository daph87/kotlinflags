package com.example.countrieskotlin.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {

    private val BASE_URL = "https://raw.githubusercontent.com"

  fun getCountriesService(): CountriesApi = Retrofit.Builder()
        .baseUrl(BASE_URL) // URL on which every endpoint will be appended
        // GSON Converter Factory to parse JSON and construct Objects
        .addConverterFactory(GsonConverterFactory.create())
        .build() // Generate the Retrofit instance
        .create(CountriesApi::class.java) // Create the CountriesApi with the Retrofit Configuration
}

