package com.example.countrieskotlin.di

import com.example.countrieskotlin.api.CountriesService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CountriesService)
}