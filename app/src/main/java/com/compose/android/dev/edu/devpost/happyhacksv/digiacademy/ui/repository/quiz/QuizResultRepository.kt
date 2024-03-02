package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.repository.quiz

import androidx.lifecycle.LiveData
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.room.QuizResult
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.roomDB.QuizResultDao
import javax.inject.Inject

class QuizResultRepository @Inject constructor(private val quizResultDao: QuizResultDao) {
    val quizResult: LiveData<List<QuizResult>> = quizResultDao.getAllResults()

    val totalMarks: LiveData<Int> = quizResultDao.getTotalMarks()
    val quizAttend: LiveData<Int> = quizResultDao.getQuizAttend()
    val marks: LiveData<Int> = quizResultDao.getMarks()
    suspend fun insertResult(quizResult: QuizResult){
        quizResultDao.insertResult(quizResult)
    }

    suspend fun deleteResult(quizResult: QuizResult){
        quizResultDao.deleteResult(quizResult)
    }
}