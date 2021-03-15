package ru.netology.nmedia

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.netology.nmedia.data.Post
import ru.netology.nmedia.databinding.PostCardBinding

class PostsAdapter(private val postListener: PostListener)
    : androidx.recyclerview.widget.ListAdapter<Post, PostViewHolder>(PostDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, postListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}