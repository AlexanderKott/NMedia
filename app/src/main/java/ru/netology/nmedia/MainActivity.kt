package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ru.netology.nmedia.data.App
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodels.ModelFactory
import ru.netology.nmedia.viewmodels.PostViewModel


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*     // val viewModel by viewModels { ModelFactory(App.getApp().getDatabase()) }

              val viewModel = ViewModelProvider(this, ModelFactory(App.getApp().getDatabase()))
                  .get(PostViewModel::class.java)*/


       /* val viewModel = ViewModelProviders.of(
            this,
            ModelFactory(App.getApp().getDatabase())
        ).get(PostViewModel::class.java)*/

    }


}