package ru.netology.nmedia.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.netology.nmedia.data.App
import ru.netology.nmedia.data.PostsRoomDatabase


class ModelFactory(private val base: PostsRoomDatabase) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(base, App.getApp()) as T
        }  
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

