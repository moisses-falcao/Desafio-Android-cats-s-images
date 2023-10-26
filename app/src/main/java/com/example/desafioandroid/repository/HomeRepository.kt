package com.example.desafioandroid.repository

import com.example.desafioandroid.statepattern.ImageStatus

interface HomeRepository {

    suspend fun getImages() : ImageStatus
}