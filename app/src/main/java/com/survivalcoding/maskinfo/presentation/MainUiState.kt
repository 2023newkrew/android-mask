package com.survivalcoding.maskinfo.presentation

import com.survivalcoding.maskinfo.domain.model.Photo

data class MainUiState(
    val photos: List<Photo> = listOf(),
    val isLoading: Boolean = false,
    val query: String = "apple"
)