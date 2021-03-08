package ru.netology.nmedia

import androidx.lifecycle.LiveData

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likePost(id: Int)
    fun sharePost(id: Int)
    fun removePost(id: Int)
    fun newPost(text: String)
    fun markPostForEdit(post: Post)
    fun cancelEditing()
}