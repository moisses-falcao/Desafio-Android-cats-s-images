package com.example.desafioandroid.statepattern

import com.example.desafioandroid.model.ImageModel

sealed class ImageStatus {

    data class Success(val listOfImageModel : List<ImageModel>) : ImageStatus()

    data class Error(val error: Throwable) : ImageStatus()

    object NotFound : ImageStatus()
}
