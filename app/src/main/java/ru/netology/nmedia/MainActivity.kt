package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager

import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodels.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel : PostViewModel by viewModels()
        val adapter = PostsAdapter ({
            viewModel.like(it.id)
        }, {viewModel.share(it.id)} )

        binding.list.adapter = adapter
        binding.list.setLayoutManager (LinearLayoutManager(this) )

   viewModel.data.observe(this,{ posts ->
     adapter.submitList(posts)
   })
    }
}