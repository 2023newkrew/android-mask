package com.survivalcoding.maskinfo.data.repository

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class PhotoRepositoryTest {
    private val repository = PhotoRepository()

    @Test
    fun `사진 정보를 잘 가져와야 한다`() {
        println(repository.hashCode())
        val result = repository.getPhotos("apple")

        assertEquals(9084, result.total)
        assertEquals(500, result.totalHits)
        assertEquals(20, result.hits.size)
        assertEquals(634572, result.hits[0].id)
        assertEquals(
            "https://cdn.pixabay.com/photo/2015/02/13/00/43/apples-634572_150.jpg",
            result.hits[0].previewURL
        )
    }

    @Test
    fun `카운트는 1씩 증가해야 한다`() {
        println(repository.hashCode())
        assertEquals(0, repository.count)

        repository.increase()

        assertEquals(1, repository.count)
    }

}