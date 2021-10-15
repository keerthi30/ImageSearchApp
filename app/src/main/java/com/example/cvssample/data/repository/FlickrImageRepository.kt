package com.example.cvssample.data.repository

import androidx.lifecycle.LiveData
import com.example.cvssample.data.models.SearchTag
import com.example.cvssample.data.room.FlickrDao

class FlickrImageRepository(private val flickrApi: FlickrApi,
                            private val flickrDao: FlickrDao) {

    suspend fun getImagesByTag(tag:String) = flickrApi.getFlickrImages(tags = tag)

    fun getSearchTagsLiveData(): LiveData<List<SearchTag>> {
        return flickrDao.getAllSearchTags()
    }

    fun saveSearchTag(tag: String) {
        flickrDao.insertSearchTag(SearchTag(tag))
    }

    suspend fun deleteAllTags() {
        flickrDao.deleteAll()
    }
}