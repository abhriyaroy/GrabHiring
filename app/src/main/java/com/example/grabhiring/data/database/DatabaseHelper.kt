package com.example.grabhiring.data.database

import com.example.grabhiring.data.model.NewsDataEntity
import io.reactivex.Single

interface DatabaseHelper {
 fun getDatabase() : AppDatabase
}

const val DATABASE_NAME = ""

class DatabaseHelperImpl : DatabaseHelper {

}