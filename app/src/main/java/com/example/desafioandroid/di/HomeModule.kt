package com.example.desafioandroid.di

import com.example.desafioandroid.repository.HomeRepository
import com.example.desafioandroid.repository.HomeRepositoryImpl
import com.example.desafioandroid.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    single<HomeRepository> {
        HomeRepositoryImpl(imageService = get())
    }

    viewModel {
        HomeViewModel(homeRepository = get())
    }
}