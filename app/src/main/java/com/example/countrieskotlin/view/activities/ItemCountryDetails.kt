package com.example.countrieskotlin.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.countrieskotlin.R
import com.example.countrieskotlin.view.fragments.getProgressDrawable
import com.example.countrieskotlin.view.fragments.loadImage
import kotlinx.android.synthetic.main.activity_item_country_details.*

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
        val button = findViewById<Button>(R.id.fav_button)
        button.setOnClickListener{
            view -> addToFav(view)
        }

    }
    private fun addToFav(view : View){
        Log.e("test" , view.toString())
        Toast.makeText(this,"added to favorite" , Toast.LENGTH_SHORT).show()
    }
}