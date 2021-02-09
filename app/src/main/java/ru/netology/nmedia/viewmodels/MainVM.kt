package ru.netology.nmedia.viewmodels

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.PostRepositoryImpl

class MainVM : ViewModel() {
    private val repo = PostRepositoryImpl()

    val data = repo.get()
    fun like() = repo.like()
    fun share() = repo.share()
}