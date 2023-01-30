package com.survivalcoding.maskinfo.presentation

import com.survivalcoding.maskinfo.data.remote.StoreService
import com.survivalcoding.maskinfo.data.repository.StoreRepositoryImpl
import com.survivalcoding.maskinfo.domain.repository.StoreRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainViewModelTest {
    private lateinit var storeService: StoreService
    private lateinit var storeRepository: StoreRepository
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        storeService = StoreService.create()
        storeRepository = StoreRepositoryImpl(storeService)
        mainViewModel = MainViewModel(storeRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun load() = runBlocking {
        mainViewModel.load()
        Assert.assertEquals(false, mainViewModel.mainUiState.value.maskStocks.isNotEmpty())
    }

    @Test
    fun loadAfterSeconds() = runBlocking {
        mainViewModel.load()
        delay(3000)
        Assert.assertEquals(true, mainViewModel.mainUiState.value.maskStocks.isNotEmpty())
    }

}