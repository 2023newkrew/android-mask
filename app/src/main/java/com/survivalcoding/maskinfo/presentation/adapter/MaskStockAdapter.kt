package com.survivalcoding.maskinfo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.maskinfo.R
import com.survivalcoding.maskinfo.domain.model.MaskStock
import com.survivalcoding.maskinfo.databinding.MaskStockItemBinding

class MaskStockAdapter :
    ListAdapter<MaskStock, MaskStockAdapter.ViewHolder>(diffCallback) {

    class ViewHolder(val binding: MaskStockItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mask_stock_item, parent, false)
        return ViewHolder(MaskStockItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.maskStock = getItem(position)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MaskStock>() {
            override fun areItemsTheSame(oldItem: MaskStock, newItem: MaskStock): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: MaskStock, newItem: MaskStock): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }

}