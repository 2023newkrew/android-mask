package com.survivalcoding.maskinfo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.maskinfo.data.repository.BadRequestError
import com.survivalcoding.maskinfo.data.repository.NetworkError
import com.survivalcoding.maskinfo.data.repository.ServerError
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

            when (val result = getPhotosUseCase(query)) {
                is MyResult.Error -> {
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
