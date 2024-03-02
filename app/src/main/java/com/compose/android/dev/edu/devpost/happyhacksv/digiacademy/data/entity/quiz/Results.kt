package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.quiz

data class Results(
    val type: String,
    val difficulty: String,
    val category: String,
    val question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>,
)
