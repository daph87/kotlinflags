package com.example.countrieskotlin.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.countrieskotlin.R
import com.example.countrieskotlin.api.CountriesService
import com.example.countrieskotlin.model.Country
import com.example.countrieskotlin.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_item_country_details.*

class CountryDetails() : AppCompatActivity() {
    private var countryService: CountriesService = CountriesService()
    private lateinit var favCountry: MutableLiveData<Country>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_country_details)
        this.name.text = intent.getStringExtra("countryName")
        countryService.initPref(this)
        countryService.putSharedPref(intent.getStringExtra("countryName").toString())
        val countryName: String = countryService.getSharedPref().toString()
        //setCountry(countryName)
        val button = findViewById<Button>(R.id.fav_button)
        button.setOnClickListener { view ->
            addToFav(view)
        }


    }

    private suspend fun setCountry(countryName: String) {
        favCountry.value = countryService.getOneCountry(countryName)!!
    }

    private fun addToFav(view: View) {
        //Log.e("test", temp.toString())
        Toast.makeText(this, "added to favorite", Toast.LENGTH_SHORT).show()
    }
}