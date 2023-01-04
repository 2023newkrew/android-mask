package com.survivalcoding.maskinfo.data.model

import com.survivalcoding.maskinfo.R
import com.survivalcoding.maskinfo.data.MaskStockStatus

data class MaskStock(
    val place: String,
    val address: String,
    val distance: Int,
    val status: MaskStockStatus
){
    fun statusString(): String = when(status){
        MaskStockStatus.InSufficient -> "소진임박"
        MaskStockStatus.Spare -> "여유"
        MaskStockStatus.Sufficient -> "충분"
    }

    fun statusColor(): Int= when(status){
        MaskStockStatus.InSufficient -> R.color.insufficient_red
        MaskStockStatus.Spare -> R.color.spare_yellow
        MaskStockStatus.Sufficient -> R.color.sufficient_green
    }
    fun amountString(): String = when(status){
        MaskStockStatus.InSufficient -> "30개 미만"
        MaskStockStatus.Spare -> "30개 이상"
        MaskStockStatus.Sufficient -> "100개 이상"
    }
}