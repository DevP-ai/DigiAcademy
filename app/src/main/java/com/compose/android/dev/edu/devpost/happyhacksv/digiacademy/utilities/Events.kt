package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities

class Events <out T>(private val content:T) {

    var hasBeenEnable=false

    fun getContentIfNotHandle():T?{
        return if(!hasBeenEnable){
            hasBeenEnable=true
            content
        }else{
            null
        }
    }

    fun peekContent():T=content
}