package com.survivalcoding.maskinfo.presentation.ui_state

import com.survivalcoding.maskinfo.domain.model.MaskStock

data class MainUiState(
    val maskStocks: List<MaskStock> = emptyList()
)