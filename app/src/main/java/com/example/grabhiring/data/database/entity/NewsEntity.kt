package com.example.grabhiring.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.grabhiring.data.model.NewsDataEntity

const val TABLE_NAME = "news_table"
const val LAST_REFRESHED_COLUMN = "LAST_REFRESHED_AT"
const val LOCATION_COLUMN = "LOCATION"
const val DATA_COLUMN = "DATA"

@Entity(tableName = TABLE_NAME)
data class NewsEntity(
  @PrimaryKey(autoGenerate = true)
  val uid: Long,

  @ColumnInfo(name = LAST_REFRESHED_COLUMN)
  val time: Long,

  @ColumnInfo(name = LOCATION_COLUMN)
  val location: String,

  @ColumnInfo(name = DATA_COLUMN)
  val data: NewsDataEntity
)