package com.example.grabhiring.data.database

import androidx.room.Database
import com.example.grabhiring.data.database.dao.NewsDao
import com.example.grabhiring.data.database.entity.NewsEntity

const val DATABASE_VERSION = 1

@Database(entities = [(NewsEntity::class)], version = DATABASE_VERSION)
abstract class AppDatabase {
  abstract fun newsDao(): NewsDao
}