package ru.netology.nmedia

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.FragmentFeedBinding
import ru.netology.nmedia.viewmodels.PostViewModel


class FeedFragment : Fragment() {

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = { this.requireParentFragment() })

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
                viewModel.like(post.id)
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
                viewModel.share(post.id)
            }

            override fun onDeleteListener(post: Post) {
                viewModel.cancelEditing()
                viewModel.remove(post.id)

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
        })

        binding.enterText.setOnClickListener {
            viewModel.cancelEditing()
            findNavController().navigate(R.id.action_feedFragment_to_editFragment2)
        }

    }

}