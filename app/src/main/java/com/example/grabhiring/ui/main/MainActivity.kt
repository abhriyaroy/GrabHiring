package com.example.grabhiring.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.grabhiring.R
import com.example.grabhiring.data.Repository
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var repository: Repository

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}
