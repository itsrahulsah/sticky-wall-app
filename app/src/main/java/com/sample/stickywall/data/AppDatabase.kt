package com.sample.stickywall.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sample.stickywall.data.dao.NotesDao
import com.sample.stickywall.data.models.Notes

@Database(entities = [Notes::class], version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun notesDao():NotesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return if (INSTANCE == null) {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "notes_db.db"
                    ).build()
                    INSTANCE = instance
                }
                INSTANCE!!
            } else {
                INSTANCE!!
            }
        }
    }
}