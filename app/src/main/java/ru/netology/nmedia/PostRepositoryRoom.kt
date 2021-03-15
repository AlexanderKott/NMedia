package ru.netology.nmedia

import androidx.lifecycle.LiveData
import ru.netology.nmedia.data.Post

interface PostRepositoryRoom {
    fun getAll(): LiveData<List<Post>>
    fun likePost(post: Post)
    fun sharePost(post: Post)
    fun removePost(post: Post)
    fun changePost(text: String)
    fun markPostForEdit(post: Post)
    fun cancelEditing()
}