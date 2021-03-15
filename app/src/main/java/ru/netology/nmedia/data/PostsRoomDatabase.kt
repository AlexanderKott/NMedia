package ru.netology.nmedia.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Post::class], version = 3)
abstract class PostsRoomDatabase : RoomDatabase() {
    abstract fun postsTableDAO(): PostTableDAO
}