package com.cdinv.assign_call

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cdinv.assign_call.databinding.ActivityMainBinding
import com.cdinv.assign_call.models.Country

private const val TAG = "MainActivity"
const val EXTRA_POST_ID = "EXTRA_POST_ID"


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var countriesPostAdapter: CountriesPostAdapter
    private val countriesPosts = mutableListOf<Country>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.countriesData.observe(this, Observer { countries ->
            Log.i(TAG, "Number of posts: ${countries.size}")
            val numElements = countriesPosts.size
            countriesPosts.clear()
            countriesPosts.addAll(countries)
            countriesPostAdapter.notifyDataSetChanged()
            binding.rvPosts.smoothScrollToPosition(numElements)
        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            Log.i(TAG, "isLoading $isLoading")
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage == null) {
                binding.tvError.visibility = View.GONE
            } else {
                binding.tvError.visibility = View.VISIBLE
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })

        countriesPostAdapter = CountriesPostAdapter(this, countriesPosts,
            object : CountriesPostAdapter.ItemClickListener {
                override fun onItemClick(country: Country) {
                    Log.i(TAG, "Sent to Another Activity")
                }
            }
        )

        binding.rvPosts.adapter = countriesPostAdapter
        binding.rvPosts.layoutManager = LinearLayoutManager(this)

        binding.button.setOnClickListener {
            viewModel.getCountries()
        }
    }

}