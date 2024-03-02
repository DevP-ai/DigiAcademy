package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.room.QuizResult

@Dao
interface QuizResultDao {

    @Query("SELECT * FROM quizResult")
    fun getAllResults(): LiveData<List<QuizResult>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(quizResult: QuizResult)

    @Delete
    suspend fun deleteResult(quizResult: QuizResult)

    @Query("SELECT SUM(score) FROM quizResult")
    fun getTotalMarks(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM quizResult")
    fun getQuizAttend(): LiveData<Int>

    @Query("SELECT COUNT(*)*10 FROM quizResult")
    fun getMarks(): LiveData<Int>
}