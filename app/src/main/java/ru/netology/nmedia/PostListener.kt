package ru.netology.nmedia

import ru.netology.nmedia.data.Post

interface PostListener {
    fun onLikeListener (post : Post)
    fun onShareListener(post : Post)
    fun onDeleteListener(post : Post)
    fun onEditListener(post : Post)
    fun onYoutubeListener(post: Post)
}