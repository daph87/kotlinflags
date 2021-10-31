package com.example.countrieskotlin.view.fragments

import com.example.countrieskotlin.model.Country

interface CountryClickListener {
    fun onClick(data : Country)
}