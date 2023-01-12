package com.survivalcoding.maskinfo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.maskinfo.data.repository.PhotoException
import com.survivalcoding.maskinfo.domain.use_case.GetPhotosUseCase
import com.survivalcoding.maskinfo.domain.util.MyResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(MainUiState())
    val state: StateFlow<MainUiState> = _state

    init {
        fetchPhotos("apple")
    }

    fun fetchPhotos(query: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)

            val result = getPhotosUseCase(query)

            when (result) {
                is MyResult.Error -> {
                    when (result.e) {
                        is PhotoException.ServerException -> println(result.e.message)
                        is PhotoException.BadRequestException -> println(result.e.message)
                        is PhotoException.Exception -> println(result.e.message)
                        is PhotoException.NetworkException -> println(result.e.message)
                    }
                    println(result.e.message)
                }
                is MyResult.Success -> {
                    _state.value = state.value.copy(
                        photos = result.data,
                        isLoading = false,
                    )
                }
            }
        }
    }
}
