package com.example.jokeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as JokeApp).viewModel

        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.jokeTextView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val likeButton = findViewById<ImageButton>(R.id.likeButton)
        progressBar.visibility = View.INVISIBLE

        button.setOnClickListener {
            button.isEnabled = false
            progressBar.visibility = View.VISIBLE
            viewModel.getJoke()
        }

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            //viewModel.chooseFavorites(isChecked)
        }

        viewModel.init(object : DataCallback {
            override fun provideText(text: String) = runOnUiThread {
                button.isEnabled = true
                progressBar.visibility = View.INVISIBLE
                textView.text = text
            }

            override fun provideIconRes(id: Int) = runOnUiThread { likeButton.setImageResource(id) }
        })
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}