package com.example.cvssample.data.repository

import androidx.lifecycle.LiveData
import com.example.cvssample.data.models.SearchTag
import com.example.cvssample.data.room.FlickrDao

class FlickrImageRepository(private val flickrApi: FlickrApi) {

    suspend fun getImagesByTag(tag:String) = flickrApi.getFlickrImages(tags = tag)

//    suspend fun getSearchTags(): LiveData<List<SearchTag>> {
//        return flickrDao.getAllSearchTags()
//    }
//
//    suspend fun saveSearchTag(tag: String) {
//        flickrDao.insertSearchTag(
//            SearchTag(tag)
//        )
//    }
//
//    suspend fun deleteAllTags() {
//        flickrDao.deleteAll()
//    }
}