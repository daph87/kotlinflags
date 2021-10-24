package com.example.countrieskotlin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countrieskotlin.R
import com.example.countrieskotlin.model.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(var countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    fun updateCountries(newCountries : List<Country>){
        countries.clear()
        countries.addAll(newCountries)

        // recreate the entire adapter
        notifyDataSetChanged()
    }
    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageView = view.imageView
        private val countryName = view.name
//        private val countryCapital = view.capital
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country) {
            // Bind the Country Name
            countryName.text = country.countryName
            // Bind the Country Capital
//            countryCapital.text = country.capital
            // Load the Image onto the ImageView
            imageView.loadImage(country.flag, progressDrawable)
        }
    }
}


