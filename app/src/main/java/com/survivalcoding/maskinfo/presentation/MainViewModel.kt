package com.survivalcoding.maskinfo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.maskinfo.domain.use_case.GetPhotosUseCase
import com.survivalcoding.maskinfo.util.MyResult
import com.survivalcoding.maskinfo.util.PhotoException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState())
    val state: StateFlow<MainUiState> = _state

    private val _event = MutableSharedFlow<MainUiEvent>()
    val event = _event.asSharedFlow()

    init {
        fetchPhotos("apple")
    }

    fun fetchPhotos(query: String) {
        viewModelScope.launch {
            _event.emit(MainUiEvent.ShowSnackBar("삐그덕"))

            _state.value = state.value.copy(isLoading = true)

            val result = getPhotosUseCase(query)
            _event.emit(MainUiEvent.ShowSnackBar("삐그덕 2"))

            when (result) {
                is MyResult.Error -> {
                    when (result.e) {
                        is PhotoException.ServerException -> println(result.e.message)
                        is PhotoException.BadRequestException -> println(result.e.message)
                        is PhotoException.Exception -> println(result.e.message)
                        is PhotoException.NetworkException -> println(result.e.message)
                    }
                    println(result.e.message)
                    // 에러
                    _event.emit(MainUiEvent.ShowSnackBar(result.e.message ?: "Unknown Error"))
                }
                is MyResult.Success -> {
                    _state.value = state.value.copy(
                        photos = result.data,
                        isLoading = false,
                    )
                    // 성공
                    _event.emit(MainUiEvent.DataLoadSuccess)
                }
            }
        }
    }
}

sealed class MainUiEvent {
    data class ShowSnackBar(val message: String) : MainUiEvent()
    object DataLoadSuccess : MainUiEvent()
}