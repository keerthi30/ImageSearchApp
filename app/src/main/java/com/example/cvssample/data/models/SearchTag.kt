package com.example.cvssample.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchtag")
data class SearchTag(@PrimaryKey val tag: String)