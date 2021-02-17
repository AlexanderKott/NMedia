package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isGone

import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodels.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(object : PostListener {
            override fun onLikeListener(post: Post) {
                viewModel.like(post.id)
            }

            override fun onShareListener(post: Post) {
                viewModel.share(post.id)
            }

            override fun onDeleteListener(post: Post) {
                with(binding.editText) {
                    viewModel.remove(post.id)
                    setText("")
                    clearFocus()
                    binding.cancelEditing.isGone = true
                    hideKeyboard()
                }
            }

            override fun onEditListener(post: Post) {
                viewModel.editPost(post)
                binding.editText.setText(post.body)
                binding.editText.requestFocus()
                binding.cancelEditing.isGone = false
            }
        })


        binding.cancelEditing.setOnClickListener {
            viewModel.cancelEditing()
            binding.editText.setText("")
            binding.editText.clearFocus()
            binding.cancelEditing.isGone = true
            it.hideKeyboard()
        }

        binding.list.adapter = adapter

        viewModel.data.observe(this, { posts ->
            adapter.submitList(posts)
        })

        binding.enterText.setOnClickListener {
            with(binding.editText) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(this@MainActivity,
                            context.getString(R.string.empty_new_text),
                            Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                viewModel.changePost(text.toString().trim())
                clearFocus()
                setText("")
                hideKeyboard()
            }


        }

    }
}