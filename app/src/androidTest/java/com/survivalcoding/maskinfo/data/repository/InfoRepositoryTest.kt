package com.survivalcoding.maskinfo.data.repository

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class InfoRepositoryTest {
    private val infoRepository: InfoRepositoryImpl by lazy { InfoRepositoryImpl() }

    @Test
    fun testGetInfoList() = runBlocking {
        val mask = infoRepository.getMask(currentPage = 2)
        Assert.assertTrue(mask.stores.isNotEmpty())
        Assert.assertEquals(mask.stores[0].name, "선경약국")
    }
}