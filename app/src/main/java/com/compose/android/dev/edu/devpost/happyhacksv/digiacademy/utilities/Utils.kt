package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.databinding.ProgressDialogBinding

object Utils {

    private var dialog: AlertDialog?=null

    fun showDialog(context: Context, message:String){
        val progress= ProgressDialogBinding.inflate(LayoutInflater.from(context))

        progress.tvMessage.text=message
        dialog= AlertDialog.Builder(context)
            .setView(progress.root)
            .setCancelable(false)
            .create()
        dialog!!.show()
    }

    fun hideDialog(){
        dialog!!.dismiss()
    }
    val listOfNumberOfQuestion= listOf(
        1,2,3,4,5,6,7,8,9,10
    )

    val listOfCategory= listOf(
        "General Knowledge",
        "Entertainment: Books",
        "Entertainment: Film",
        "Entertainment: Music",
        "Science & Nature",
        "Science: Computers",
        "Mythology",
        "Sports",
        "Geography",
        "History",
        "Animals",
        "Vehicles"
    )

    val listDifficulty=listOf(
        "Easy",
        "Medium",
        "Hard"
    )
    val listOfTypes=listOf(
        "Multiple Choice"
    )

}