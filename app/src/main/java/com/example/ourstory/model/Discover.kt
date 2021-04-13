package com.example.ourstory.model

import android.os.Parcel
import android.os.Parcelable

data class Discover (
    var id: Int = 0,
    var title: String? = null,
    var description: String? = null,
    var rating: Int = 0,
    var image: String? = null

    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(rating)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Discover> {
        override fun createFromParcel(parcel: Parcel): Discover {
            return Discover(parcel)
        }

        override fun newArray(size: Int): Array<Discover?> {
            return arrayOfNulls(size)
        }
    }
}