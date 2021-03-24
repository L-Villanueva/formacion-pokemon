package com.example.pokemon.home_activity.ui.home.ui

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.models.PokemonDTO
import com.example.pokemon.R
import com.example.pokemon.commons.DetailAdapter
import com.example.pokemon.databinding.PokemonItemBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonAdapter(private var mValues: List<PokemonDTO>? , private val setOnClick: OnCLickListener, private val context: Context): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private lateinit var binding: PokemonItemBinding
    private lateinit var adapter: DetailAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.ViewHolder {
        binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int) {
        mValues?.let {
            holder.tvName.text = it[position].name.capitalize()
            CoroutineScope(Dispatchers.Main).launch {
                Picasso.get()
                        .load(it[position].sprites?.front_default)
                        .resize(200,200)
                        .into(holder.image)
            }
            holder.itemView.setOnClickListener { _ ->
                setOnClick.click(it[position])
            }
            val layout = LinearLayoutManager(context)
            layout.orientation = RecyclerView.HORIZONTAL
            holder.types.layoutManager = layout
            adapter = DetailAdapter(it[position].types)
            holder.types.adapter = adapter
        } ?: clearList()
    }

    override fun getItemCount(): Int {
        return mValues?.size ?: 0
    }

    inner class ViewHolder (mView: View): RecyclerView.ViewHolder(mView){
        val tvName = binding.tvPokemonName
        val image = binding.ivPokemonSprite
        val types = binding.typeRecycler
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
