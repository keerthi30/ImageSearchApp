package com.example.cvssample.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cvssample.data.models.SearchTag

@Database(entities = [SearchTag::class], version = 1)
abstract class FlickrDatabase: RoomDatabase() {
    abstract fun flickrDao(): FlickrDao
}