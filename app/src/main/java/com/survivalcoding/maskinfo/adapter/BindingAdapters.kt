package com.survivalcoding.maskinfo.adapter

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.survivalcoding.maskinfo.R

@BindingAdapter("amount")
fun setAmount(
    textView: TextView,
    amount: Int,
) {
    textView.text = when (amount) {
        in 0 until 30 -> "30개 미만"
        in 30 until 100 -> "30개 이상"
        else -> "100개 이상"
    }

    textView.setTextColor(
        ContextCompat.getColor(
            textView.context,
            when (amount) {
                in 0 until 30 -> R.color.insufficient_red
                in 30 until 100 -> R.color.spare_yellow
                else -> R.color.sufficient_green
            }
        )
    )
}

@BindingAdapter("state")
fun setState(
    textView: TextView,
    amount: Int,
) {
    when (amount) {
        in 0 until 30 -> {
            textView.text = "소진임박"
            textView.setTextColor(
                ContextCompat.getColor(
                    textView.context,
                    R.color.insufficient_red
                )
            )
        }
        in 30 until 100 -> {
            textView.text = "여유"
            textView.setTextColor(
                ContextCompat.getColor(
                    textView.context,
                    R.color.spare_yellow
                )
            )
        }
        else -> {
            textView.text = "충분"
            textView.setTextColor(
                ContextCompat.getColor(
                    textView.context,
                    R.color.sufficient_green
                )
            )
        }
    }
}