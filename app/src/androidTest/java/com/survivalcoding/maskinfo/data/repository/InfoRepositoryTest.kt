package com.survivalcoding.maskinfo.data.repository

import com.survivalcoding.maskinfo.data.model.Info
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class InfoRepositoryTest {
    private val infoRepository: InfoRepository by lazy { InfoRepository() }

    @Test
    fun testGetInfoList() {
        // initialize arguments
        val infoList = ArrayList<Info>()
        val currentPage = 1
        val userCoordinate = null

        // test
        runBlocking {
            infoRepository.getInfoList(infoList, currentPage, userCoordinate)
            Assert.assertTrue(infoList.size > 0)
            Assert.assertEquals(infoList[0].name, "선경약국")
        }
    }
}