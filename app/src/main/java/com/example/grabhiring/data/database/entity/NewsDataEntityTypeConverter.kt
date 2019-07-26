package com.example.grabhiring.data.database.entity

import androidx.room.TypeConverter
import com.example.grabhiring.data.model.NewsDataEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NewsDataEntityTypeConverter {

  companion object {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToArticleList(string: String): NewsDataEntity {
      return gson.fromJson(string, object : TypeToken<NewsDataEntity>() {}.type)
    }

    @TypeConverter
    @JvmStatic
    fun articleListToString(newsDataEntity: NewsDataEntity): String {
      return gson.toJson(
        newsDataEntity, object : TypeToken<NewsDataEntity>() {}.type
      )
    }
  }
}