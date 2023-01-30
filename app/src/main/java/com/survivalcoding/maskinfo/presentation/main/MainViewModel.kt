package com.survivalcoding.maskinfo.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.maskinfo.domain.model.Coordinate
import com.survivalcoding.maskinfo.domain.model.Info
import com.survivalcoding.maskinfo.data.mapper.toInfo
import com.survivalcoding.maskinfo.data.repository.InfoRepositoryImpl
import com.survivalcoding.maskinfo.domain.use_case.GetMaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getMaskUseCase: GetMaskUseCase) : ViewModel() {
    private var _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()
    var userCoordinate: Coordinate? = null

    fun loadInfoList(initialize: Boolean) {
        if (initialize) {
            _state.value = state.value.copy(
                infoList = listOf(),
                currentPage = 1
            )
        }
        viewModelScope.launch {
            try {
                val infoList = state.value.infoList.toMutableList()
                getMaskUseCase(state.value.currentPage).stores.map {
                    it.toInfo(userCoordinate)?.let { info ->
                        infoList.add(info)
                    }
                }
                _state.value = state.value.copy(
                    infoList = infoList,
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
    val infoList: List<Info> = listOf(),
    val currentPage: Int = 1
)