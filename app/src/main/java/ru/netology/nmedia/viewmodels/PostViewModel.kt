package ru.netology.nmedia.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.netology.nmedia.Post
import ru.netology.nmedia.PostRepositoryImpl

class PostViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = PostRepositoryImpl(app.applicationContext)

    val data = repo.getAll()
    fun like(id : Int) = repo.likePost(id)
    fun share(id : Int) = repo.sharePost(id)
    fun remove(id : Int) = repo.removePost(id)
    fun changePost(txt : String) = repo.newPost(txt)
    fun markPostForEdit(post : Post) = repo.markPostForEdit(post)
    fun cancelEditing()  = repo.cancelEditing()
}