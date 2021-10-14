package com.example.cvssample.data.models

import com.google.gson.annotations.SerializedName

data class ListOfImages(
    val description: String,
    val generator: String,
    @SerializedName("items")
    val flickrImages: List<FlickrImage>,
    val link: String,
    val modified: String,
    val title: String
)