package com.example.desafioandroid.di

import com.example.desafioandroid.constants.Constants.BASE_URL
import com.example.desafioandroid.service.ImageService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single(named("BASE_URL")){
        BASE_URL
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(ImageService::class.java)
    }
}