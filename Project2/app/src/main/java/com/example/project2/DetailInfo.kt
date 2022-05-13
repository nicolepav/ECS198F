package com.example.project2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailInfo (
    val id : String,
    val name : String,
    val imageUrl : String,
    val location : String,
    val priceLeVel : Int,
    val openTime : String,
    val closeTime : String
) : Parcelable