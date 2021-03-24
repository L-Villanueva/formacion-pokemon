package com.example.pokemon.commons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.models.Types
import com.example.pokemon.R
import com.example.pokemon.databinding.TypeItemBinding

    class DetailAdapter(private var mValues: List<Types>?): RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

        private lateinit var binding: TypeItemBinding

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            binding = TypeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding.root)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            mValues?.let {
                when (it[position].type?.name) {

                    "bug" -> {
                        holder.typeImage.setImageResource(R.drawable.bug)
                    }
                    "electric" -> {
                        holder.typeImage.setImageResource(R.drawable.electric)
                    }
                    "dragon" -> {
                        holder.typeImage.setImageResource(R.drawable.dragon)
                    }
                    "flying" -> {
                        holder.typeImage.setImageResource(R.drawable.flying)
                    }
                    "dark" -> {
                        holder.typeImage.setImageResource(R.drawable.dark)
                    }
                    "normal" -> {
                    holder.typeImage.setImageResource(R.drawable.normal)
                    }
                    "water" -> {
                        holder.typeImage.setImageResource(R.drawable.water)
                    }
                    "psychic" -> {
                        holder.typeImage.setImageResource(R.drawable.psychic)
                    }
                    "rock" -> {
                        holder.typeImage.setImageResource(R.drawable.rock)
                    }
                    "ground" -> {
                        holder.typeImage.setImageResource(R.drawable.ground)
                    }
                    "fairy" -> {
                        holder.typeImage.setImageResource(R.drawable.fairy)
                    }
                    "grass" -> {
                        holder.typeImage.setImageResource(R.drawable.grass)
                    }
                    "fighting" -> {
                        holder.typeImage.setImageResource(R.drawable.fighting)
                    }
                    "ghost" -> {
                        holder.typeImage.setImageResource(R.drawable.ghost)
                    }
                    "ice" -> {
                        holder.typeImage.setImageResource(R.drawable.ice)
                    }
                    "poison" -> {
                        holder.typeImage.setImageResource(R.drawable.poison)
                    }
                    "steel" -> {
                        holder.typeImage.setImageResource(R.drawable.steel)
                    }
                    "fire" -> {
                        holder.typeImage.setImageResource(R.drawable.fire)
                    }
                }
            } ?: clearList()
        }

        override fun getItemCount(): Int {
            return mValues?.size ?: 0
        }

        inner class ViewHolder (mView: View): RecyclerView.ViewHolder(mView){
           val typeImage = binding.tvType
        }

        private fun clearList() {
            val emptyList = listOf<Types>()
            mValues = emptyList
            notifyItemRangeRemoved(0, 0)
        }
    }

