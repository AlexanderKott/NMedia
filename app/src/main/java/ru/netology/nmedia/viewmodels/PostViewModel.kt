package ru.netology.nmedia.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import ru.netology.nmedia.PostRepositoryPrefsAndMemoryImpl
import ru.netology.nmedia.data.Post
import ru.netology.nmedia.PostRepositoryRoomImpl
import ru.netology.nmedia.data.PostsRoomDatabase

class PostViewModel(base: PostsRoomDatabase, app: Application) : AndroidViewModel(app) {
    //  private val repo = PostRepositoryPrefsAndMemoryImpl(app.applicationContext)
    private val repo = PostRepositoryRoomImpl(base)

    init {
        Log.e("abc", " this is a new viewmodel")
    }

    val data = repo.getAll()
    fun like(post: Post) = repo.likePost(post)
    fun share(post: Post) = repo.sharePost(post)
    fun remove(post: Post) = repo.removePost(post)
    fun changePost(txt: String) = repo.changePost(txt)
    fun markPostForEdit(post: Post) = repo.markPostForEdit(post)
    fun cancelEditing() = repo.cancelEditing()


}