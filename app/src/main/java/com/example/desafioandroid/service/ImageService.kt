package com.example.desafioandroid.service

import com.example.desafioandroid.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageService {

    @Headers("Authorization: Client-ID 1ceddedc03a5d71")
    @GET("/3/gallery/search/?q=cats")
    suspend fun getImages() : ResponseModel
}