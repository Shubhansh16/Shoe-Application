package com.example.shoeapplication.models

import android.os.Parcel
import android.os.Parcelable
import java.util.LinkedList

data class ItemModel(
    var title:String="",
    var description:String="",
    var picUrl:ArrayList<String> = ArrayList(),
    var size:ArrayList<String> = ArrayList(),
    var price:Double =0.0,
    var rating:Double =0.0,
    var numberInCart:Int =0

):Parcelable {
    constructor(parcel: Parcel):this (
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeStringList(picUrl)
        parcel.writeStringList(size)
        parcel.writeDouble(price)
        parcel.writeDouble(rating)
    }

    companion object CREATOR : Parcelable.Creator<ItemModel> {
        override fun createFromParcel(parcel: Parcel): ItemModel {
            return ItemModel(parcel)
        }

        override fun newArray(size: Int): Array<ItemModel?> {
            return arrayOfNulls(size)
        }
    }
}
