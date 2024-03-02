package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}