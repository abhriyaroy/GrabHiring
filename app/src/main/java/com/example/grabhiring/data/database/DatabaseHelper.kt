package com.example.grabhiring.data.database

import android.content.Context
import androidx.room.Room

interface DatabaseHelper {
  fun getDatabase(): AppDatabase
}

const val DATABASE_NAME = "AppDatabase.db"

class DatabaseHelperImpl(private val context: Context) : DatabaseHelper {

  private var databaseInstance: AppDatabase? = null

  override fun getDatabase(): AppDatabase {
    if (databaseInstance == null) {
      databaseInstance =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
    }
    return databaseInstance!!
  }
}