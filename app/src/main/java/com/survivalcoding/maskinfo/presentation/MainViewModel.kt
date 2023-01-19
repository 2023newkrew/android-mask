package com.survivalcoding.maskinfo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.maskinfo.domain.model.Coordinate
import com.survivalcoding.maskinfo.domain.model.StoreInfo
import com.survivalcoding.maskinfo.data.repository.InfoRepositoryImpl
import com.survivalcoding.maskinfo.domain.use_case.GetInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getInfoUseCase: GetInfoUseCase) : ViewModel() {
    private var _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()
    var userCoordinate: Coordinate? = null

    fun loadInfoList(initialize: Boolean) {
        if (initialize) {
            _state.value = state.value.copy(
                storeInfoList = listOf(),
                currentPage = 1
            )
        }
        viewModelScope.launch {
            try {
                val infoList = state.value.storeInfoList.toMutableList()
                getInfoUseCase(
                    currentPage = state.value.currentPage,
                    userCoordinate = userCoordinate
                ).map {
                    if (it != null) {
                        infoList.add(it)
                    }
                }
                _state.value = state.value.copy(
                    storeInfoList = infoList,
                    currentPage = state.value.currentPage + 1
                )
            } catch (exception: Exception) {
                when (exception) { // case invalid data
                    is ClassCastException -> println(exception.stackTrace)
                    is JSONException -> println(exception.stackTrace)
                    is IOException -> println(exception.stackTrace)
                    else -> println(exception.stackTrace)
                }
            }
        }
    }
}

data class MainState(
    val storeInfoList: List<StoreInfo> = listOf(),
    val currentPage: Int = 1
)