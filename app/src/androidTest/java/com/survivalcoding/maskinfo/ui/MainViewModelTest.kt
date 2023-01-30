package com.survivalcoding.maskinfo.ui

import com.survivalcoding.maskinfo.data.repository.StoreRepositoryImpl
import com.survivalcoding.maskinfo.data.datasource.MaskDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainViewModelTest {
    private lateinit var maskDataSource: MaskDataSource
    private lateinit var storeRepositoryImpl: StoreRepositoryImpl
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        maskDataSource = MaskDataSource.create()
        storeRepositoryImpl = StoreRepositoryImpl(maskDataSource)
        mainViewModel = MainViewModel(storeRepositoryImpl)
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