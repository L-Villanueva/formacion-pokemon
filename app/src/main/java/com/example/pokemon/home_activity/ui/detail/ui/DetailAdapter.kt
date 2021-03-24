package com.example.pokemon.home_activity.ui.detail.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.models.Types
import com.example.pokemon.databinding.TypeItemBinding

    class DetailAdapter(private var mValues: List<Types>?): RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

        private lateinit var binding: TypeItemBinding

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapter.ViewHolder {
            binding = TypeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding.root)
        }

        override fun onBindViewHolder(holder: DetailAdapter.ViewHolder, position: Int) {
            mValues?.let {
                holder.typeName.text = it[position].type?.name
            } ?: clearList()
        }

        override fun getItemCount(): Int {
            return mValues?.size ?: 0
        }

        inner class ViewHolder (mView: View): RecyclerView.ViewHolder(mView){
           val typeName = binding.tvType
        }

        private fun clearList() {
            val emptyList = listOf<Types>()
            mValues = emptyList
            notifyItemRangeRemoved(0, 0)
        }
    }

