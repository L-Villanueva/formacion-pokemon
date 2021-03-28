package com.example.pokemon.home_activity.ui.detail.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.models.Stats
import com.example.pokemon.databinding.StatItemBinding


class StatsAdapter(private var mValues: List<Stats>?): RecyclerView.Adapter<StatsAdapter.ViewHolder>() {

    private lateinit var binding: StatItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = StatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mValues?.let {

            holder.statName.text = it[position].stat.name
            holder.statValue.text = it[position].base_stat.toString()

        } ?: clearList()
    }

    override fun getItemCount(): Int {
        return mValues?.size ?: 0
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val statName = binding.tvStatName
        val statValue = binding.tvStatValue
    }

    private fun clearList() {
        val emptyList = listOf<Stats>()
        mValues = emptyList
        notifyItemRangeRemoved(0, 0)
    }
}