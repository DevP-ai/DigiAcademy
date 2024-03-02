package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Source(
    val id:String?="",
    val name:String?=""
): Parcelable, Serializable
