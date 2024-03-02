package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
    }
}