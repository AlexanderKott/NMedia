package ru.netology.nmedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import ru.netology.nmedia.data.App
import ru.netology.nmedia.databinding.FragmentEditBinding
import ru.netology.nmedia.viewmodels.ModelFactory
import ru.netology.nmedia.viewmodels.PostViewModel


class EditFragment : Fragment() {

    private  val viewModel: PostViewModel  by navGraphViewModels(R.id.nav_main)

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(vie: View, savedInstanceState: Bundle?) {

        binding.editText.setText(arguments?.getString("postT") )
        binding.editText.requestFocus()

        binding.fab.setOnClickListener { view ->
            if (binding.editText.text.isNotBlank()) {
                val content = binding.editText.text.toString()
                viewModel.changePost(content)
                viewModel.cancelEditing()
                findNavController().popBackStack(R.id.feedFragment, false)
            }
        }

    }


}