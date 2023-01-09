package com.survivalcoding.maskinfo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.maskinfo.data.model.Photo
import com.survivalcoding.maskinfo.data.remote.mapper.toPhoto
import com.survivalcoding.maskinfo.data.repository.PhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = PhotoRepository()

    private var _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    init {
        fetchPhotos("apple")
    }

    fun fetchPhotos(query: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)

            val result = repository.getPhotos(query)

            _state.value = state.value.copy(
                photos = result.hits.map { it.toPhoto() },
                isLoading = false,
            )
        }
    }
}

data class MainState(
    val photos: List<Photo> = listOf(),
    val isLoading: Boolean = false,
)