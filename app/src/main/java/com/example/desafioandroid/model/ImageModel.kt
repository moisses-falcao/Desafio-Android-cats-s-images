package com.example.desafioandroid.model
import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("link") val link: String?,
    @SerializedName("is_album") val isAlbum: Boolean = false,
    @SerializedName("images") val images: List<ImageModel>? = null
)



