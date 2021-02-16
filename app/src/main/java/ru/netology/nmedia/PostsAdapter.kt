package ru.netology.nmedia

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.netology.nmedia.databinding.PostCardBinding

typealias  OnLikeListener = (post: Post) -> Unit
typealias  OnShareListener = (post: Post) -> Unit

class PostsAdapter(val likeListener :OnLikeListener, val shareListener : OnShareListener)
    : androidx.recyclerview.widget.ListAdapter<Post, PostViewHolder>(PostDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, likeListener, shareListener )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}