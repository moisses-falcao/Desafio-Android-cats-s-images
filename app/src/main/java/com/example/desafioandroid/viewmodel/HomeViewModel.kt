package com.example.desafioandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioandroid.statepattern.ImageStatus
import com.example.desafioandroid.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel (val homeRepository: HomeRepository) : ViewModel() {

    private val _status = MutableLiveData<ImageStatus>()
    val status: LiveData<ImageStatus> get() = _status

    fun getImages() {
        viewModelScope.launch {
            when (val response = homeRepository.getImages()) {
                is ImageStatus.Success -> {
                    _status.value = ImageStatus.Success(response.listOfImageModel)
                }
                is ImageStatus.NotFound -> {
                    _status.value = ImageStatus.NotFound
                }
                is ImageStatus.Error -> {
                    _status.value = ImageStatus.Error(response.error)
                }
            }
        }
    }
}