package com.example.desafioandroid.repository

import com.example.desafioandroid.service.ImageService
import com.example.desafioandroid.statepattern.ImageStatus
import java.lang.Exception

class HomeRepositoryImpl (val imageService: ImageService) : HomeRepository {

    override suspend fun getImages() : ImageStatus {

        return try {

            val response = imageService.getImages()

            if (!response.data.isNullOrEmpty()){
                ImageStatus.Success(response.data)
            } else{
                ImageStatus.NotFound
            }
        } catch (e: Exception) {
            ImageStatus.Error(e)
        }
    }
}