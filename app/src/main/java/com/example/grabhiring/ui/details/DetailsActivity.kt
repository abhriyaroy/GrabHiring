package com.example.grabhiring.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.grabhiring.R

class DetailsActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    overridePendingTransition(R.anim.slide_from_right, R.anim.no_change)
    setContentView(R.layout.activity_details)
  }

  override fun onBackPressed() {
    overridePendingTransition(R.anim.no_change, R.anim.slide_to_right)
    super.onBackPressed()
  }
}
