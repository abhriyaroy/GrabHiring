package com.example.grabhiring.data.model

import android.os.Parcel
import android.os.Parcelable

data class NewsDataEntity(
  val status: String,
  val totalResults: Int,
  val articles: ArrayList<ArticlesDataEntity>
) : Parcelable {
  private val articledList = mutableListOf<ArticlesDataEntity>()

  constructor(parcel: Parcel) : this(
    parcel.readString()!!,
    parcel.readInt(),
    parcel.readTypedList(articledList, ArticlesDataEntity.CREATOR)
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(status)
    parcel.writeInt(totalResults)
    parcel.writeList(articles.toMutableList())
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<NewsDataEntity> {
    override fun createFromParcel(parcel: Parcel): NewsDataEntity {
      return NewsDataEntity(parcel)
    }

    override fun newArray(size: Int): Array<NewsDataEntity?> {
      return arrayOfNulls(size)
    }
  }
}