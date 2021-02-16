package ru.netology.nmedia.viewmodels

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.PostRepositoryImpl

class PostViewModel : ViewModel() {
    private val repo = PostRepositoryImpl()

    val data = repo.getAll()
    fun like(id : Int) = repo.like(id)
    fun share(id : Int) = repo.share(id)
}