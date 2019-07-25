package com.example.grabhiring.data.model

import android.os.Parcel
import android.os.Parcelable

data class ArticlesDataEntity(
  val source: SourceDataEntity,
  val author: String,
  val title: String,
  val description: String,
  val url: String,
  val urlToImage: String,
  val publishedAt: String,
  val content: String
) : Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readParcelable(SourceDataEntity::class.java.classLoader)!!,
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeParcelable(source, flags)
    parcel.writeString(author)
    parcel.writeString(title)
    parcel.writeString(description)
    parcel.writeString(url)
    parcel.writeString(urlToImage)
    parcel.writeString(publishedAt)
    parcel.writeString(content)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<ArticlesDataEntity> {
    override fun createFromParcel(parcel: Parcel): ArticlesDataEntity {
      return ArticlesDataEntity(parcel)
    }

    override fun newArray(size: Int): Array<ArticlesDataEntity?> {
      return arrayOfNulls(size)
    }
  }
}