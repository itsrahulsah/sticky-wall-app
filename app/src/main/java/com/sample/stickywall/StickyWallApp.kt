package com.sample.stickywall

import android.app.Application
import androidx.room.Room
import com.sample.stickywall.data.AppDatabase

class StickyWallApp:Application() {

    companion object {
        lateinit var appDatabase: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "notes_db"
        ).build()
    }
}