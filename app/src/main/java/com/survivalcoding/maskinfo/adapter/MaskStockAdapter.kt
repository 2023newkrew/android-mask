package com.survivalcoding.maskinfo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.maskinfo.MaskStock
import com.survivalcoding.maskinfo.R

class MaskStockAdapter(private val context: Context, private val dataset: ArrayList<MaskStock>) :
    RecyclerView.Adapter<MaskStockAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeTextView: TextView
        val addressTextView: TextView
        val distanceTextView: TextView
        val stateTextView: TextView
        val maskAmountTextView: TextView

        init {
            placeTextView = view.findViewById(R.id.placeTextView)
            addressTextView = view.findViewById(R.id.addressTextView)
            distanceTextView = view.findViewById(R.id.distanceTextView)
            stateTextView = view.findViewById(R.id.stateTextView)
            maskAmountTextView = view.findViewById(R.id.maskAmountTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mask_stock_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.placeTextView.text = dataset[position].place
        holder.addressTextView.text = dataset[position].address
        holder.distanceTextView.text = String.format("%.1fkm", dataset[position].distance / 1000.0)
        when (dataset[position].maskAmount) {
            in 0 until 30 -> {
                holder.maskAmountTextView.text = "30개 미만"
                holder.maskAmountTextView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.insufficient_red
                    )
                )
                holder.stateTextView.text = "소진임박"
                holder.stateTextView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.insufficient_red
                    )
                )
            }
            in 30 until 100 -> {
                holder.maskAmountTextView.text = "30개 이상"
                holder.maskAmountTextView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.spare_yellow
                    )
                )
                holder.stateTextView.text = "여유"
                holder.stateTextView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.spare_yellow
                    )
                )
            }
            else -> {
                holder.maskAmountTextView.text = "100개 이상"
                holder.maskAmountTextView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.sufficient_green
                    )
                )
                holder.stateTextView.text = "충분"
                holder.stateTextView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.sufficient_green
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}