package com.triputranto.newsapp.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.data.source.local.AppDatabase.Companion.DB_VERSION


/**
 * Created by Ahmad Tri Putranto on 01/01/2021.
 * */
@Database(
    entities = [Articles::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "NewsApp.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase =
            INSTANCE
                ?: synchronized(this) {
                    INSTANCE
                        ?: buildDatabase(
                            context
                        )
                            .also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME
            ).allowMainThreadQueries()
                .build()
    }
}