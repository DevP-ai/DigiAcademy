package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.viewmodel.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.room.QuizResult
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.repository.quiz.QuizResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val quizResultRepository: QuizResultRepository
) : ViewModel() {

    val quizResult: LiveData<List<QuizResult>> = quizResultRepository.quizResult
    val totalMarks: LiveData<Int> = quizResultRepository.totalMarks
    val quizAttend: LiveData<Int> = quizResultRepository.quizAttend
    val marks: LiveData<Int> = quizResultRepository.marks

    fun insertQuizResult(quizResult: QuizResult) {
        viewModelScope.launch {
            quizResultRepository.insertResult(quizResult)
        }
    }

    fun deleteQuizResult(quizResult: QuizResult) {
        viewModelScope.launch {
            quizResultRepository.deleteResult(quizResult)
        }
    }

}