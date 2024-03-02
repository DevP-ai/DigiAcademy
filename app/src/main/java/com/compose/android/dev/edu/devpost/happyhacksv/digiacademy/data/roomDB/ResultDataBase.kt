package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.room.QuizResult

@Database(entities = [QuizResult::class], version = 1, exportSchema = false)
abstract class ResultDataBase: RoomDatabase(){
    abstract fun quizResultDao():QuizResultDao

    companion object{
        private const val FROM_VERSION=1
        private const val TO_VERSION=2

        val MIGRATION_1_2: Migration =object : Migration(FROM_VERSION, TO_VERSION){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("DROP TABLE IF EXISTS quizResult")
                db.execSQL("CREATE TABLE IF NOT EXISTS quizResult_new (id INTEGER PRIMARY KEY AUTOINCREMENT,score INTEGER,category TEXT,difficulty TEXT)")
                db.execSQL("INSERT INTO quizResult_new(id,score,category,difficulty) SELECT id,score,category,difficulty FROM quizResult")
                db.execSQL("DROP TABLE IF EXISTS quizResult")
                db.execSQL("ALTER TABLE quizResult_new RENAME TO quizResult")
            }
        }
    }
}