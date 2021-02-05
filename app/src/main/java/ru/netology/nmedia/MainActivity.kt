package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(999, false, 2000000, 5700)
        with(binding) {
            ShareCount.text = digitToString(post.shareCount)
            likesCount.text = digitToString(post.likesCount)
            viewsCount.text = digitToString(post.viewsCount)
            if (post.like) {
                likeImg.setImageResource(R.drawable.ic_baseline_favorite_24)
            }

            likeImg.setOnClickListener {
                post.like = !post.like
                likeImg.setImageResource(
                        if (post.like) R.drawable.ic_baseline_favorite_24
                        else R.drawable.ic_baseline_favorite_border_24)
                if (post.like) {
                    post.likesCount++
                } else {
                    post.likesCount--
                }
                likesCount.text = digitToString(post.likesCount)
            }

            shareImg.setOnClickListener {
                post.shareCount += 1
                ShareCount.text = digitToString(post.shareCount)
            }

        }

    }
}