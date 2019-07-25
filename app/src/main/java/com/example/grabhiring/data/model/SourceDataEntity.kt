package com.example.grabhiring.data.model

import android.os.Parcel
import android.os.Parcelable

data class SourceDataEntity(
  val id: String,
  val name: String
) : Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readString()!!,
    parcel.readString()!!
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(id)
    parcel.writeString(name)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<SourceDataEntity> {
    override fun createFromParcel(parcel: Parcel): SourceDataEntity {
      return SourceDataEntity(parcel)
    }

    override fun newArray(size: Int): Array<SourceDataEntity?> {
      return arrayOfNulls(size)
    }
  }
}