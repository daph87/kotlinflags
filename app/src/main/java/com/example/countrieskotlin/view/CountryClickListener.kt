package com.example.countrieskotlin.view

import com.example.countrieskotlin.model.Country

interface CountryClickListener {
    fun onClick(data : Country)
}