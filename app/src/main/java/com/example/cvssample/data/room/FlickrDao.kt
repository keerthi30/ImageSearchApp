package com.example.cvssample.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cvssample.data.models.SearchTag

@Dao
interface FlickrDao {
    @Query("SELECT * FROM searchtag")
    fun getAllSearchTags(): LiveData<List<SearchTag>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchTag(searchTag: SearchTag)

    @Query("DELETE FROM searchtag")
    fun deleteAll()
}