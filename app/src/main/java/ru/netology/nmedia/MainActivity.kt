package ru.netology.nmedia

import android.R.attr.key
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodels.PostViewModel


class MainActivity : AppCompatActivity() {
    private final val editPostRequestCode: Int = 1
    private final val newPostRequestCode: Int = 2

    
    val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostsAdapter(object : PostListener {
            override fun onLikeListener(post: Post) {
                viewModel.like(post.id)
            }

            override fun onYoutubeListener(post: Post) {
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + post.videoURI))
                if (intent.resolveActivity(packageManager) == null) {
                    intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + post.videoURI)
                    )
                }
                startActivity(intent)
            }

            override fun onShareListener(post: Post) {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.body)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(intent, getString(R.string.chooser))
                startActivity(shareIntent)

                viewModel.share(post.id)


            }

            override fun onDeleteListener(post: Post) {
                viewModel.cancelEditing()
                viewModel.remove(post.id)

            }

            override fun onEditListener(post: Post) {
                viewModel.markPostForEdit(post)

                val intent = Intent(this@MainActivity, InputText::class.java).apply {
                    putExtra("postT", post.body)
                    type = "text/plain"
                }
                startActivityForResult(intent, editPostRequestCode)

            }
        })



        binding.list.adapter = adapter

        viewModel.data.observe(this, { posts ->
            adapter.submitList(posts)
        })

        binding.enterText.setOnClickListener {
                val intent = Intent(this@MainActivity, InputText::class.java)
                startActivityForResult(intent, newPostRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            newPostRequestCode -> {
                if (resultCode != Activity.RESULT_OK) {
                    return
                }

                viewModel.cancelEditing()
                data?.getStringExtra(Intent.EXTRA_TEXT)?.let {
                    viewModel.changePost(it)
                }
            }

            editPostRequestCode -> {
                if (resultCode != Activity.RESULT_OK) {
                    return
                }

                data?.getStringExtra(Intent.EXTRA_TEXT)?.let {
                    viewModel.changePost(it)
                }
            }
        }
    }
}