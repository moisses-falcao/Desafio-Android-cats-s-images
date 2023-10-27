package com.example.desafioandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafioandroid.constants.Constants.ME_CONTRATA
import com.example.desafioandroid.databinding.ImageModelBinding
import com.example.desafioandroid.model.ImageModel

class HomeImageAdapter (val imagesList : List<ImageModel>) : RecyclerView.Adapter<HomeImageAdapter.ImagesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagesViewHolder {
        val binding = ImageModelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        with(holder) {
            with(imagesList[position]) {
                if(link != ME_CONTRATA){
                    if (isAlbum) {
                        val firstImageLink = images?.firstOrNull()?.link
                        Glide.with(binding.imageModelImageView).load(firstImageLink).into(binding.imageModelImageView)
                    } else {
                        Glide.with(binding.imageModelImageView).load(link).into(binding.imageModelImageView)
                    }
                }
            }
        }
    }


    override fun getItemCount(): Int = imagesList.size

    class ImagesViewHolder(val binding : ImageModelBinding) : RecyclerView.ViewHolder(binding.root)
}