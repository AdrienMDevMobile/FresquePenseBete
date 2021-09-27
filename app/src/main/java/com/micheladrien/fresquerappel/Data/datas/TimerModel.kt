package com.micheladrien.fresquerappel.Data.datas

import android.os.Parcel
import android.os.Parcelable

//Le time value du TimerModel est un int des secondes. La traduction seconds en HH:MM:SS se fera par la VM.
class TimerModel(val id : Int, var name:String?, var time_value:Int)  : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(time_value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TimerModel> {
        override fun createFromParcel(parcel: Parcel): TimerModel {
            return TimerModel(parcel)
        }

        override fun newArray(size: Int): Array<TimerModel?> {
            return arrayOfNulls(size)
        }
    }

}