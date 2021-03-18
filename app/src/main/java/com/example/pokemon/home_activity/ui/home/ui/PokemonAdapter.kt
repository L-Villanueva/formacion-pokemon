package com.example.pokemon.home_activity.ui.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.models.TransactionDTO
import com.example.pokemon.databinding.PokemonItemBinding

class PokemonAdapter(private var mValues: List<TransactionDTO>?): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private lateinit var binding: PokemonItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.ViewHolder {
        binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int) {
        mValues?.let {
            holder.tvName.text = it[position].description
        } ?: clearList()
    }

    override fun getItemCount(): Int {
        return mValues?.size ?: 0
    }

    inner class ViewHolder (mView: View): RecyclerView.ViewHolder(mView){
        val tvName = binding.tvPokemonName
    }

    private fun clearList() {
        val emptyList = listOf<TransactionDTO>()
        mValues = emptyList
        notifyItemRangeRemoved(0, 0)
    }
}