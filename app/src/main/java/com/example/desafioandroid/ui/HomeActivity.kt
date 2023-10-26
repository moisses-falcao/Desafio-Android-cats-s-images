package com.example.desafioandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.desafioandroid.adapter.HomeImageAdapter
import com.example.desafioandroid.viewmodel.HomeViewModel
import com.example.desafioandroid.statepattern.ImageStatus
import com.example.desafioandroid.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private val homeViewModel : HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    fun setupRecyclerView(){

        homeViewModel.getImages()

        homeViewModel.status.observe(this) {
            when(it){
                is ImageStatus.Success -> {
                    val homeAdapter = HomeImageAdapter(it.listOfImageModel)
                    binding.homeImagesRecyclerView.adapter = homeAdapter
                    Log.e("BATATA", it.listOfImageModel.toString())
                }
                is ImageStatus.NotFound -> {
                    Toast.makeText(this, "Não foi possível encontrar as imagens no momento, verifique sua conexão ou tente mais tarde", Toast.LENGTH_LONG).show()
                }
                is ImageStatus.Error -> {
                    Toast.makeText(this, "Erro: ${it.error.message}", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }
}