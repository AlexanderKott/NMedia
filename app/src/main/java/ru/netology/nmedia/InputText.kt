package ru.netology.nmedia

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import ru.netology.nmedia.databinding.ActivityInputTextBinding


class InputText : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityInputTextBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val str = intent.extras?.getString("postT")
        if (str != null){
            binding.editText.setText(str)
        }

        binding.editText.requestFocus()


        binding.fab.setOnClickListener { view ->
             val intent = Intent()
            if (binding.editText.text.isBlank()){
                setResult(Activity.RESULT_CANCELED)
            } else {
                val content = binding.editText.text.toString()
                intent.putExtra(Intent.EXTRA_TEXT, content)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
}