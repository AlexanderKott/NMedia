package ru.netology.nmedia

import android.util.Log
import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.databinding.PostCardBinding

class PostViewHolder(
    private val binding: PostCardBinding,
    private val postListener: PostListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post : Post){
        binding.apply {

            shareImg.text = digitToString(post.shareCount)   //
            likeImg.text = digitToString(post.likesCount)
            viewsImg.text = digitToString(post.viewsCount)

             PostBody.text = post.body
             caption.text = post.caption
             date.text = post.published
             likeImg.isChecked = post.like


        }
        binding.more.setOnClickListener {
            PopupMenu(it.context, it).apply {
                inflate(R.menu.options)
                setOnMenuItemClickListener { item ->

                    when(item.itemId){
                        R.id.delete -> {
                            postListener.onDeleteListener(post)
                            true
                        }
                        R.id.edit -> {
                            postListener.onEditListener(post)
                            true
                        }

                        else -> false
                    }
                }
            }.show()
        }

        binding.likeImg.setOnClickListener {
            postListener.onLikeListener(post)

        }

        binding.youtubePrev.setOnClickListener {
            postListener.onYoutubeListener(post)

        }


        binding.shareImg.setOnClickListener {
            postListener.onShareListener(post)
        }

    }
}