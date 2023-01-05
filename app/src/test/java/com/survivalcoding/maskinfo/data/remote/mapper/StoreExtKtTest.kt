package com.survivalcoding.maskinfo.data.remote.mapper

import com.survivalcoding.maskinfo.data.remote.dto.Store
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class StoreExtKtTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun toMaskStock1() {
        val dummyStore = Store(null, "dummy", "dummy", 0.0, 0.0, "dummy", "dummy", "dummy", "dummy")

        Assert.assertEquals(null, dummyStore.toMaskStock(0.0f, 0.0f))
    }

    @Test
    fun toMaskStock2() {
        val dummyStore =
            Store("dummy", "dummy", "dummy", null, 0.0, "dummy", "dummy", "dummy", "dummy")

        Assert.assertEquals(null, dummyStore.toMaskStock(0.0f, 0.0f))
    }

    @Test
    fun distanceTo1() {
        val dummyStore =
            Store("dummy", "dummy", "dummy", 0.0, 0.0, "dummy", "dummy", "dummy", "dummy")

        Assert.assertEquals(0.0f, dummyStore.distanceTo(0.0f, 0.0f))
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun distanceTo2() {
        val dummyStore =
            Store("dummy", "dummy", "dummy", null, 0.0, "dummy", "dummy", "dummy", "dummy")
        dummyStore.distanceTo(0.0f, 0.0f)
    }
}