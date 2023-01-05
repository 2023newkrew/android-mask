package com.survivalcoding.maskinfo.ui

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun calculateDistance() {
        val viewModel = MainViewModel()
        val testLat = 37.405221 // allen 고시원
        val testLng = 127.126398
        val result = viewModel.calculateDistance(testLat, testLng)

        assertEquals(result, 1800.0, 100.0)
    }
}