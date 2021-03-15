package ru.netology.nmedia

import androidx.lifecycle.LiveData
import ru.netology.nmedia.data.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likePost(id: Long)
    fun sharePost(id: Long)
    fun removePost(id: Long)
    fun changePost(text: String)
    fun markPostForEdit(post: Post)
    fun cancelEditing()
}