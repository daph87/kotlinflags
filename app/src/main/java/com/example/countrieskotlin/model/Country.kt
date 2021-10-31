package com.example.countrieskotlin.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val countryName: String?,

    @SerializedName("capital")
    val capital: String?,

    @SerializedName("flagPNG")
    val flag: String?,

    @SerializedName("population")
    val population: String?,
    @SerializedName("region")
    val region: String?,
    val isFavorite: Boolean = false
)
