package com.example.cvssample.data.models

import com.google.gson.annotations.SerializedName

data class FlickrImage(
    val author: String,
    @SerializedName("author_id")
    val authorId: String,
    @SerializedName("date_taken")
    val dateTaken: String,
    val description: String,
    val link: String,
    val media: Media,
    val published: String,
    val tags: String,
    val title: String
)