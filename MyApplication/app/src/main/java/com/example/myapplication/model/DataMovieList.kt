package com.example.myapplication.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName



data class DataMovie (
    var id: String? = null,
    var title: String? = null,
    var release_date: String? = null,
    var vote_average: String? = null,
    var resume: String? = null,
    var poster_path: String? = null) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(release_date)
        parcel.writeString(vote_average)
        parcel.writeString(resume)
        parcel.writeString(poster_path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataMovie> {
        override fun createFromParcel(parcel: Parcel): DataMovie {
            return DataMovie(parcel)
        }

        override fun newArray(size: Int): Array<DataMovie?> {
            return arrayOfNulls(size)
        }
    }
}