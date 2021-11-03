package com.example.countrieskotlin.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countrieskotlin.R
import com.example.countrieskotlin.model.Country
import com.example.countrieskotlin.view.fragments.CountryClickListener
import com.example.countrieskotlin.view.fragments.CountryListAdapter
import com.example.countrieskotlin.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CountryClickListener {

    private lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(this, arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Instantiate the viewModel we created, will destroy it when not needed, no memory losses
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this, { countries ->
            //checks if countries is not null
            countries?.let {
                countriesList.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it)
            }
        })
        viewModel.countryLoadError.observe(this, { isError ->
            isError?.let {
                list_error.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
        viewModel.loading.observe(this, { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    countriesList.visibility = View.GONE

                }
            }
        })
    }

    override fun onClick(data: Country) {
        val intent = Intent(this, CountryDetails::class.java).apply {
            putExtra("countryName", data.countryName)
        }
        startActivity(intent)
    }
}