package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quizResult")
data class QuizResult(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    val score:Int,
    val category:String?="",
    val difficulty:String?=""
)
