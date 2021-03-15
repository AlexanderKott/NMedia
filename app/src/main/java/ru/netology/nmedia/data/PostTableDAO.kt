package ru.netology.nmedia.data

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.netology.nmedia.data.Post

@Dao
interface PostTableDAO {
    @Query("SELECT * FROM Posts ORDER BY id DESC")
    fun getAllRecords():  LiveData<List<Post>>

    @Insert
    fun insert(t: Post)

    @Update
    fun update(t: Post)

    @Delete
    fun delete(t: Post)


}