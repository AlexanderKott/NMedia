package ru.netology.nmedia.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import ru.netology.nmedia.Post
import ru.netology.nmedia.PostRepositoryImpl

class PostViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = PostRepositoryImpl(app.applicationContext)

    init {
        Log.e("abc", " this is new viewmodel")
    }

    val data = repo.getAll()
    fun like(id : Int) = repo.likePost(id)
    fun share(id : Int) = repo.sharePost(id)
    fun remove(id : Int) = repo.removePost(id)
    fun changePost(txt : String) = repo.changePost(txt)
    fun markPostForEdit(post : Post) = repo.markPostForEdit(post)
    fun cancelEditing()  = repo.cancelEditing()
}