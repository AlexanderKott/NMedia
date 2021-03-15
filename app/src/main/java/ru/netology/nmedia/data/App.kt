package ru.netology.nmedia.data

import android.app.Application
import android.util.Log
import androidx.room.Room

class App : Application() {
    private lateinit var database: PostsRoomDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, PostsRoomDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration().build()
    }

    fun getDatabase(): PostsRoomDatabase {
        return database
    }

    companion object {
        lateinit var instance: App

        @JvmStatic
        fun getApp(): App {
            return instance
        }
    }
}