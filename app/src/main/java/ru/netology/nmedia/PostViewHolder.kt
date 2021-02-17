package ru.netology.nmedia

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.databinding.PostCardBinding

class PostViewHolder(
    private val binding: PostCardBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener,

) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post : Post){
        binding.apply {

            ShareCount.text = digitToString(post.shareCount)   //
            likesCount.text = digitToString(post.likesCount)
            viewsCount.text = digitToString(post.viewsCount)

             PostBody.text = post.body
             caption.text = post.caption
             date.text = post.published

            likeImg.setImageResource(
                if (post.like) R.drawable.ic_baseline_favorite_24
                else R.drawable.ic_baseline_favorite_border_24)
        }

        binding.likeImg.setOnClickListener {
            onLikeListener(post)

        }

        binding.shareImg.setOnClickListener {
            onShareListener(post)
        }

    }
}