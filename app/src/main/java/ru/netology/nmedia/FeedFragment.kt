package ru.netology.nmedia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import ru.netology.nmedia.data.App
import ru.netology.nmedia.data.Post
import ru.netology.nmedia.databinding.FragmentFeedBinding
import ru.netology.nmedia.viewmodels.ModelFactory
import ru.netology.nmedia.viewmodels.PostViewModel


class FeedFragment : Fragment() {

    private  val viewModel: PostViewModel  by navGraphViewModels(R.id.nav_main) {
        ModelFactory(App.getApp().getDatabase())
    }

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(vie: View, savedInstanceState: Bundle?) {


        val adapter = PostsAdapter(object : PostListener {
            override fun onLikeListener(post: Post) {
                viewModel.like(post)
            }

            override fun onYoutubeListener(post: Post) {
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + post.videoURI))
                activity?.packageManager?.let {
                    if (intent.resolveActivity(it) != null) {
                        intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://www.youtube.com/watch?v=" + post.videoURI)
                        )
                    }

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
                viewModel.share(post)
            }

            override fun onDeleteListener(post: Post) {
                 viewModel.cancelEditing()
                viewModel.remove(post)

            }

            override fun onEditListener(post: Post) {
                viewModel.markPostForEdit(post)
                val bundle = bundleOf("postT" to post.body)
                findNavController().navigate(R.id.action_feedFragment_to_editFragment2, bundle)

            }
        })


        binding.list.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner, { posts ->
            adapter.submitList(posts)
            Log.e("abc","posts= ${posts.size}")
         })

        binding.enterText.setOnClickListener {
            viewModel.cancelEditing()
            findNavController().navigate(R.id.action_feedFragment_to_editFragment2)
        }

    }

}