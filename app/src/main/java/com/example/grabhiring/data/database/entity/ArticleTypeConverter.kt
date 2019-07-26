package com.example.grabhiring.data.database.entity

import androidx.room.TypeConverter
import com.example.grabhiring.data.model.ArticlesDataEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ArticleTypeConverter {

  private val gson = Gson()

  @TypeConverter
  @JvmStatic
  fun stringToArticleList(string: String): ArrayList<ArticlesDataEntity> {
    return gson.fromJson(string, object : TypeToken<ArrayList<ArticlesDataEntity>>() {}.type)
  }

  @TypeConverter
  @JvmStatic
  fun articleListToString(articlesDataEntityList: ArrayList<ArticlesDataEntity>): String {
    return gson.toJson(
      articlesDataEntityList, object : TypeToken<ArrayList<ArticlesDataEntity>>() {}.type
    )
  }
}