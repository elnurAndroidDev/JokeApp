package com.example.jokeapp

import androidx.annotation.DrawableRes


class BaseJoke(text: String, punchline: String) : Joke(text, punchline) {
    override fun getIconResId() = R.drawable.heart
}

class FavJoke(text: String, punchline: String) : Joke(text, punchline) {
    override fun getIconResId() = R.drawable.heart_filled
}

class FailedJoke(text: String) : Joke(text, "") {
    override fun getIconResId() = 0
}

abstract class Joke(private val text: String, private val punchline: String) {
    fun getJokeUI() = "$text\n$punchline"

    @DrawableRes
    abstract fun getIconResId(): Int

    fun map(callback: DataCallback) = callback.run {
        provideText(getJokeUI())
        provideIconRes(getIconResId())
    }
}