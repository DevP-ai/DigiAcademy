package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.news

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Article(

    val author:String?="",
    val title:String?="",
    val description:String?="",
    val url:String?="",
    val urlToImage:String?="",
    val publishedAt:String?="",
    val content:String?="",
    val source: Source?
): Parcelable, Serializable
