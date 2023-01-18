package com.survivalcoding.maskinfo.data.repository

import com.survivalcoding.maskinfo.domain.model.Photo
import com.survivalcoding.maskinfo.domain.repository.PhotoRepository
import com.survivalcoding.maskinfo.util.MyResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class MockPhotoRepository : PhotoRepository {
    override suspend fun getPhotos(query: String): MyResult<List<Photo>> {
        TODO("Not yet implemented")
    }

}

class PhotoRepositoryTest {
    private val repository = MockPhotoRepository()

    @Test
    fun `사진 정보를 잘 가져와야 한다`() = runBlocking {
        println(repository.hashCode())
        val result = repository.getPhotos("apple")

//        assertEquals(9085, result.total)
//        assertEquals(500, result.totalHits)
//        assertEquals(20, result.hits.size)
//        assertEquals(634572, result.hits[0].id)
//        assertEquals(
//            "https://cdn.pixabay.com/photo/2015/02/13/00/43/apples-634572_150.jpg",
//            result.hits[0].previewURL
//        )
    }

}