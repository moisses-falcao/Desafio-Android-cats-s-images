package com.example.desafioandroid.viewmodel

import androidx.lifecycle.Observer
import com.example.desafioandroid.model.ImageModel
import com.example.desafioandroid.model.ResponseModel
import com.example.desafioandroid.repository.HomeRepositoryImpl
import com.example.desafioandroid.service.ImageService
import com.example.desafioandroid.statepattern.ImageStatus
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class HomeViewModelTest{

    private var repositoryImpl: HomeRepositoryImpl = mockk()
    private lateinit var viewModel: HomeViewModel
    private val liveDataState: Observer<ImageStatus> = spyk()

    @Test
    fun `testingSuccess`() = runBlocking {

        //Given
        coEvery { repositoryImpl.getImages() } returns ImageStatus.Success(RESPONSE_MODEL.data)

        viewModel = HomeViewModel(repositoryImpl)
        viewModel.status.observeForever(liveDataState)

        //When
        viewModel.getImages()

        //Then
        assertEquals(ImageStatus.Success(RESPONSE_MODEL.data), viewModel.status.value)

        verify { liveDataState.onChanged(ImageStatus.Success(RESPONSE_MODEL.data)) }
        coVerify { repositoryImpl.getImages() }
    }

    @Test
    fun `testingNotFound`() = runBlocking {

        //Given
        coEvery { repositoryImpl.getImages() } returns ImageStatus.NotFound

        viewModel = HomeViewModel(repositoryImpl)
        viewModel.status.observeForever(liveDataState)

        //When
        viewModel.getImages()

        //Then
        assertEquals(ImageStatus.NotFound, viewModel.status.value)

        verify { liveDataState.onChanged(ImageStatus.NotFound) }
        coVerify { repositoryImpl.getImages() }
    }

    @Test
    fun `testingError`() = runBlocking {

        //Given
        coEvery { repositoryImpl.getImages() } returns ImageStatus.Error(ERROR)

        viewModel = HomeViewModel(repositoryImpl)
        viewModel.status.observeForever(liveDataState)

        //When
        viewModel.getImages()

        //Then
        assertEquals(ImageStatus.Error(ERROR), viewModel.status.value)

        verify { liveDataState.onChanged(ImageStatus.Error(ERROR)) }
        coVerify { repositoryImpl.getImages() }
    }

    companion object{
        val RESPONSE_MODEL = ResponseModel(listOf(
            ImageModel("link=https://imgur.com/a/AIkuKfn", isAlbum=true, images= listOf(
                ImageModel("link=https://i.imgur.com/ixd5Ypu.jpg", isAlbum=false, images=null)
            ))
        ))
        val ERROR: Throwable = Exception("Me contrata aí vai!")
    }
}