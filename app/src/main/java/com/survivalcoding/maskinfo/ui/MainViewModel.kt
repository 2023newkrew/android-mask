package com.survivalcoding.maskinfo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.maskinfo.data.model.Photo
import com.survivalcoding.maskinfo.data.remote.mapper.toPhoto
import com.survivalcoding.maskinfo.data.repository.PhotoRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = PhotoRepository()

    private var _state = MutableLiveData(MainState())
    val state: LiveData<MainState> = _state

    init {
        fetchPhotos("apple")
    }


    fun fetchPhotos(query: String) {
        viewModelScope.launch {
            _state.postValue(
                state.value?.copy(isLoading = true)
            )

            val result = repository.getPhotos(query)

            _state.postValue(
                state.value?.copy(
                    photos = result.hits.map { it.toPhoto() },
                    isLoading = false,
                )
            )
        }
    }
}

data class MainState(
    val photos: List<Photo> = listOf(),
    val isLoading: Boolean = false,
)