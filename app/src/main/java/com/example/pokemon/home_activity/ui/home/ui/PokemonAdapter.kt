package com.example.pokemon.home_activity.ui.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.models.PokemonDTO
import com.example.pokemon.R
import com.example.pokemon.databinding.PokemonItemBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonAdapter(private var mValues: List<PokemonDTO>? , private val setOnClick: OnCLickListener): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private lateinit var binding: PokemonItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.ViewHolder {
        binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int) {
        mValues?.let {
            holder.tvName.text = it[position].name
            CoroutineScope(Dispatchers.Main).launch {
                Picasso.get()
                        .load(it[position].sprites?.front_default)
                        .resize(200,200)
                        .into(holder.image)
            }
            holder.itemView.setOnClickListener { _ ->
                setOnClick.click(it[position])
            }
        } ?: clearList()
    }

    override fun getItemCount(): Int {
        return mValues?.size ?: 0
    }

    inner class ViewHolder (mView: View): RecyclerView.ViewHolder(mView){
        val tvName = binding.tvPokemonName
        val image = binding.ivPokemonSprite
    }

    private fun clearList() {
        val emptyList = listOf<PokemonDTO>()
        mValues = emptyList
        notifyItemRangeRemoved(0, 0)
    }
}

interface OnCLickListener {
    fun click(pokemon: PokemonDTO)
}
