package com.survivalcoding.maskinfo.data.remote.mapper

import com.survivalcoding.maskinfo.data.model.MaskStock
import com.survivalcoding.maskinfo.data.model.MaskStockStatus
import com.survivalcoding.maskinfo.data.remote.dto.Store

fun Store.toMaskStock(): MaskStock? {
    if (name == null || addr == null || remain_stat == null) return null
    return MaskStock(
        name, addr, 1000, when (remain_stat) {
            "few" -> MaskStockStatus.InSufficient
            "some" -> MaskStockStatus.Spare
            "plenty" -> MaskStockStatus.Sufficient
            else -> return null
        }
    )
}