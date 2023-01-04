package com.survivalcoding.maskinfo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.maskinfo.R
import com.survivalcoding.maskinfo.data.model.Store
import com.survivalcoding.maskinfo.databinding.MaskStockItemBinding

class StoreAdapter :
    PagingDataAdapter<Store, StoreAdapter.ViewHolder>(diffCallback) {

    class ViewHolder(val binding: MaskStockItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mask_stock_item, parent, false)
        return ViewHolder(MaskStockItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.store = getItem(position)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Store>() {
            override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem.code == newItem.code
            }

            override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem.code == newItem.code
            }
        }
    }

}