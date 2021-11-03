package com.example.countrieskotlin.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.countrieskotlin.R
import com.example.countrieskotlin.api.CountriesService
import com.example.countrieskotlin.model.Country
import com.example.countrieskotlin.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_item_country_details.*

class CountryDetails() : AppCompatActivity() {
    private var countryService: CountriesService = CountriesService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_country_details)
        this.name.text = intent.getStringExtra("countryName")
        //need to change the shared prefrence
        //----------------------------------
        //countryService.putSharedPref("afganistan")
       // Log.e("checking pref",countryService.toString())
       // countryService.putSharedPref(intent.getStringExtra("countryName").toString())
       // Log.e("checking pref",countryService.getSharedPref().toString())
        val button = findViewById<Button>(R.id.fav_button)
        button.setOnClickListener{
            view -> addToFav(view,ListViewModel().countries.value)
        }


    }
    private fun addToFav(view: View, temp: List<Country>?){
        Log.e("test" , temp.toString())
        Toast.makeText(this,"added to favorite" , Toast.LENGTH_SHORT).show()
    }
}