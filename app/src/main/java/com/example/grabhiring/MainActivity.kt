package com.example.grabhiring

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.grabhiring.data.api.NewsApiClientFactory
import com.example.grabhiring.data.database.DatabaseHelper
import com.example.grabhiring.data.database.entity.NewsEntity
import dagger.android.AndroidInjection
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var databaseHelper: DatabaseHelper
  @Inject
  lateinit var newsApiClientFactory: NewsApiClientFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    newsApiClientFactory.getNews()
      .flatMap {
        databaseHelper.getDatabase().newsDao().deleteAllCache()
        Single.just("asd")

      }.flatMap {
        databaseHelper.getDatabase().newsDao()
          .getCache()
      }
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({
        if (it.isNotEmpty())
        textView.text = it[0].data.articles[0].description
      }, {
        println(it.message)
      })
  }
}
