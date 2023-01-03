package com.survivalcoding.maskinfo.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.maskinfo.R
import com.survivalcoding.maskinfo.data.Info
import com.survivalcoding.maskinfo.databinding.ItemInfoBinding

class InfoListAdapter : ListAdapter<Info, InfoListAdapter.ViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Info>() {
            override fun areItemsTheSame(oldItem: Info, newItem: Info): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Info, newItem: Info): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(val binding: ItemInfoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_info, parent, false)
        return ViewHolder(ItemInfoBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.nameTextView.text = currentList[position].name
        holder.binding.addressTextView.text = currentList[position].address
        val distance = "${currentList[position].distance}km"
        holder.binding.distanceTextView.text = distance

        when (currentList[position].stock) {
            "few" -> {
                holder.binding.enoughTextView.setText(R.string.enough_few)
                holder.binding.stockTextView.setText(R.string.stock_few)
                holder.binding.enoughTextView.setTextColor(Color.RED)
                holder.binding.stockTextView.setTextColor(Color.RED)
            }
            "some" -> {
                holder.binding.enoughTextView.setText(R.string.enough_some)
                holder.binding.stockTextView.setText(R.string.stock_some)
                holder.binding.enoughTextView.setTextColor(Color.YELLOW)
                holder.binding.stockTextView.setTextColor(Color.YELLOW)
            }
            "plenty" -> {
                holder.binding.enoughTextView.setText(R.string.enough_plenty)
                holder.binding.stockTextView.setText(R.string.stock_plenty)
                holder.binding.enoughTextView.setTextColor(Color.GREEN)
                holder.binding.stockTextView.setTextColor(Color.GREEN)
            }
        }
    }

    override fun getItemCount() = currentList.size
}