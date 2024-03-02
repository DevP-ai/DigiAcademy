package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.api.quiz

import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.quiz.Questions
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApiService {
    @GET("/api.php?")
    suspend fun getAllQuestion(
        @Query("amount")amount:Int,
        @Query("category")category:Int,
        @Query("difficulty") difficulty : String,
        @Query("type") type : String
    ): Response<Questions>
}