package com.survivalcoding.maskinfo.data.remote.mapper

import com.survivalcoding.maskinfo.data.remote.dto.Store
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@Suppress("NonAsciiCharacters", "SpellCheckingInspection")
class StoreExtKtTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `Store의 addr이 null일 때 toMaskStock이 null 반환 하는지`() {
        val dummyStore = Store(null, "dummy", "dummy", 0.0, 0.0, "dummy", "dummy", "dummy", "dummy")

        Assert.assertEquals(null, dummyStore.toMaskStock(0.0f, 0.0f))
    }

    @Test
    fun `Store의 lat가 null일 때 toMaskStock이 null 반환 하는지`() {
        val dummyStore =
            Store("dummy", "dummy", "dummy", null, 0.0, "dummy", "dummy", "dummy", "dummy")

        Assert.assertEquals(null, dummyStore.toMaskStock(0.0f, 0.0f))
    }

    @Test
    fun `Store의 lng가 null일 때 toMaskStock이 null 반환 하는지`() {
        val dummyStore =
            Store("dummy", "dummy", "dummy", 0.0, null, "dummy", "dummy", "dummy", "dummy")

        Assert.assertEquals(null, dummyStore.toMaskStock(0.0f, 0.0f))
    }

    @Test
    fun `위도 경도가 같을 때 계산식에 오류가 나지 않는지`() {
        val dummyStore =
            Store("dummy", "dummy", "dummy", 0.0, 0.0, "dummy", "dummy", "dummy", "dummy")

        Assert.assertEquals(0.0f, dummyStore.distanceTo(0.0f, 0.0f))
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun `Store의 lat가 null일 때 distanceTo가 예외를 발생 시키는지`() {
        val dummyStore =
            Store("dummy", "dummy", "dummy", null, 0.0, "dummy", "dummy", "dummy", "dummy")
        dummyStore.distanceTo(0.0f, 0.0f)
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun `Store의 lng가 null일 때 distanceTo가 예외를 발생 시키는지`() {
        val dummyStore =
            Store("dummy", "dummy", "dummy", 0.0, null, "dummy", "dummy", "dummy", "dummy")
        dummyStore.distanceTo(0.0f, 0.0f)
    }
}