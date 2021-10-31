package com.example.countrieskotlin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.util.Util
import com.example.countrieskotlin.R
import kotlinx.android.synthetic.main.activity_item_country_details.*
import kotlinx.android.synthetic.main.activity_item_country_details.view.*
import kotlinx.android.synthetic.main.item_country.view.*

class ItemCountryDetails() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_country_details)
        val name  = intent.getStringExtra("countryName")
        val capital  = intent.getStringExtra("countryCapital")
        val region = intent.getStringExtra("countryRegion")
        val population = intent.getStringExtra("countryPopulation")
        val flag = intent.getStringExtra("countryFlag")
        this.name.text = name
        this.capital.text = capital
        this.region.text = region
        this.population.text = population
        this.flag.loadImage(flag, getProgressDrawable(this))


    }
}