package com.cdinv.assign_call

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cdinv.assign_call.models.Country

class CountriesPostAdapter(
    private val context: Context,
    private val countries: List<Country>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<CountriesPostAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(country: Country)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_countries, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val tvId = itemView.findViewById<TextView>(R.id.tvId)
        private val tvCountry = itemView.findViewById<TextView>(R.id.tvCountry)
        private val tvCities = itemView.findViewById<TextView>(R.id.tvCities)


        fun bind(country: Country) {
//            tvId.text = "Country 1"
            tvCountry.text = country.country
            tvCities.text = country.cities.joinToString(", ")
            itemView.setOnClickListener {
                itemClickListener.onItemClick(country)
            }
        }
    }

}