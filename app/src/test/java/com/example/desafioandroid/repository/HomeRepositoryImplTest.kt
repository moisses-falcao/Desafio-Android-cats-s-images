package com.example.desafioandroid.repository

import com.example.desafioandroid.model.ImageModel
import com.example.desafioandroid.model.ResponseModel
import com.example.desafioandroid.service.ImageService
import com.example.desafioandroid.statepattern.ImageStatus
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class HomeRepositoryImplTest {

    private lateinit var repositoryImpl: HomeRepositoryImpl

    @Test
    fun `testingSuccess`() = runBlocking {

        //Given
        val service: ImageService = mockk()
        coEvery { service.getImages() } returns RESPONSE_MODEL

        repositoryImpl = HomeRepositoryImpl(service)

        //When
        val result = repositoryImpl.getImages()

        //Then
        assertEquals(ImageStatus.Success(RESPONSE_MODEL.data), result)
    }

    @Test
    fun `testingNotFound`() = runBlocking {

        //Given
        val service: ImageService = mockk()
        coEvery { service.getImages() } returns ResponseModel(emptyList())

        repositoryImpl = HomeRepositoryImpl(service)

        //When
        val result = repositoryImpl.getImages()

        //Then
        assertEquals(ImageStatus.NotFound, result)
    }

    @Test
    fun `testingError`() = runBlocking {

        //Given
        val service: ImageService = mockk()
        coEvery { service.getImages() } throws ERROR

        repositoryImpl = HomeRepositoryImpl(service)

        //When
        val result = repositoryImpl.getImages()

        //Then
        assertEquals(ImageStatus.Error(ERROR), result)
    }

    companion object{
        val RESPONSE_MODEL = ResponseModel(listOf(ImageModel("link=https://imgur.com/a/AIkuKfn", isAlbum=true, images= listOf(ImageModel("link=https://i.imgur.com/ixd5Ypu.jpg", isAlbum=false, images=null)))))
        val ERROR: Throwable = Exception("Me contrata aí vai!")
    }
}