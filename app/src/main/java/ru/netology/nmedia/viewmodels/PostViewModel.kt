package ru.netology.nmedia.viewmodels

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.Post
import ru.netology.nmedia.PostRepositoryImpl

class PostViewModel : ViewModel() {
    private val repo = PostRepositoryImpl()

    val data = repo.getAll()
    fun like(id : Int) = repo.likePost(id)
    fun share(id : Int) = repo.sharePost(id)
    fun remove(id : Int) = repo.removePost(id)
    fun changePost(txt : String) = repo.newPost(txt)
    fun markPostForEdit(post : Post) = repo.editPost(post)
    fun cancelEditing()  = repo.cancelEditing()
}