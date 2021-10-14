package com.example.cvssample.data.repository

import com.example.cvssample.data.models.ListOfImages
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    @GET("/services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    suspend fun getFlickrImages(@Query("tags") tags:String): ListOfImages
}