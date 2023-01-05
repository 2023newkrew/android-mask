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

    private var _photos = MutableLiveData(listOf<Photo>())
    val photos: LiveData<List<Photo>> = _photos

    fun fetchPhotos(query: String) {
        viewModelScope.launch {
            val result = repository.getPhotos(query)

            _photos.postValue(result.hits.map { it.toPhoto() })
        }
    }
}