package com.example.countrieskotlin.di

import com.example.countrieskotlin.model.CountriesService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CountriesService)
}