package com.kalpv.turtlemintdemo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kalpv.turtlemintdemo.data.model.Comment
import com.kalpv.turtlemintdemo.data.model.Issues


@Database(entities = [Issues::class, Comment::class], version = 1)
@TypeConverters(value = [Converters::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun getIssuesDao(): IssuesDao
    abstract fun getCommentsDao(): CommentsDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "turtlemint.db"
        ).fallbackToDestructiveMigration().build()
    }

}