package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodels.MainVM

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel : MainVM by viewModels()

   viewModel.data.observe(this,{ post ->
       with(binding) {
           ShareCount.text = digitToString(post.shareCount)   //
           likesCount.text = digitToString(post.likesCount)
           viewsCount.text = digitToString(post.viewsCount)
           likeImg.setImageResource(
               if (post.like) R.drawable.ic_baseline_favorite_24
               else R.drawable.ic_baseline_favorite_border_24)
       }
   })


        binding.likeImg.setOnClickListener {
            viewModel.like()
        }

        binding.shareImg.setOnClickListener {
            viewModel.share()
        }

    }
}