package com.survivalcoding.maskinfo.data

import com.survivalcoding.maskinfo.data.remote.StoreService
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@Suppress("NonAsciiCharacters")
class StoreRepositoryTest {
    private lateinit var storeService: StoreService
    private lateinit var storeRepository: StoreRepository

    @Before
    fun setUp() {
        storeService = StoreService.create()
        storeRepository = StoreRepository(storeService)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun maskStores() = runBlocking {
        val testStores = storeRepository.maskStores()
        assertEquals(true, testStores.isNotEmpty())
    }

    @Test
    fun `약국과 상태가 매칭가되는지`() = runBlocking {
        val testStores = storeRepository.maskStores()
        assertEquals(true, testStores.any { it.name == "건강비타민약국" && it.remain_stat == "plenty" })
    }

    @Test
    fun `약국과 주소가 매칭되는지`() = runBlocking {
        val testStores = storeRepository.maskStores()
        assertEquals("서울특별시 강북구 솔매로 59 (미아동)", testStores.first { it.name == "건강비타민약국" }.addr)
    }
}