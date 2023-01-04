package com.survivalcoding.maskinfo.data.extension

import com.survivalcoding.maskinfo.R
import com.survivalcoding.maskinfo.data.model.MaskStock
import com.survivalcoding.maskinfo.data.model.MaskStockStatus

fun MaskStock.statusString(): String = when (status) {
    MaskStockStatus.InSufficient -> "소진임박"
    MaskStockStatus.Spare -> "여유"
    MaskStockStatus.Sufficient -> "충분"
}

fun MaskStock.statusColor(): Int = when (status) {
    MaskStockStatus.InSufficient -> R.color.insufficient_red
    MaskStockStatus.Spare -> R.color.spare_yellow
    MaskStockStatus.Sufficient -> R.color.sufficient_green
}

fun MaskStock.amountString(): String = when (status) {
    MaskStockStatus.InSufficient -> "30개 미만"
    MaskStockStatus.Spare -> "30개 이상"
    MaskStockStatus.Sufficient -> "100개 이상"
}