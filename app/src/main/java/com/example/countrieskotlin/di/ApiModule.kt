package com.example.countrieskotlin.di

import com.example.countrieskotlin.model.CountriesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://raw.githubusercontent.com"
@Provides
    fun provideCountriesApi(): CountriesApi {
       return Retrofit.Builder()
            .baseUrl(BASE_URL) // URL on which every endpoint will be appended
            // GSON Converter Factory to parse JSON and construct Objects
            .addConverterFactory(GsonConverterFactory.create())
            .build() // Generate the Retrofit instance
            .create(CountriesApi::class.java) // Create the CountriesApi with the Retrofit Configuration
    }
}