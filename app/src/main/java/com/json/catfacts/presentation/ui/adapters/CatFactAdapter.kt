package com.json.catfacts.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.json.catfacts.R
import com.json.catfacts.data.entities.CatFact
import com.json.catfacts.databinding.AdapterCatfactBinding
import javax.inject.Inject

class CatFactAdapter @Inject constructor() : RecyclerView.Adapter<CatFactAdapter.CatFactViewHolder>() {

    var catFactsList = mutableListOf<CatFact>()
    inner class CatFactViewHolder(val binding: AdapterCatfactBinding) : RecyclerView.ViewHolder(binding.root)

    fun setFacts(facts: List<CatFact>) {
        val previousPosition = this.catFactsList.size
        this.catFactsList = facts.toMutableList()
        notifyItemRangeChanged(previousPosition, catFactsList.size)
    }

    fun removeItem(position: Int){
        val previousPosition = this.catFactsList.size
        catFactsList.removeAt(position)
        notifyItemRangeChanged(previousPosition, catFactsList.size)
    }

    fun getItem(position: Int) = this.catFactsList.get(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFactViewHolder =
        CatFactViewHolder(
            AdapterCatfactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = catFactsList.size

    override fun onBindViewHolder(holder: CatFactViewHolder, position: Int) {
        holder.binding.apply {
            val factObj = catFactsList[position]
            catFact.text = factObj.fact
            Glide.with(holder.itemView.context)
                .load(factObj.url)
                .placeholder(R.drawable.paw)
                .into(imageview)
        }
    }
}
