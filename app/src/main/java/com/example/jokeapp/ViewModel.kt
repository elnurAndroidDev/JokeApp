package com.example.jokeapp

class ViewModel(private val model: Model) {
    private var dataCallback: DataCallback? = null

    fun init(callback: DataCallback) {
        this.dataCallback = callback
        model.init(object : JokeCallback {
            override fun provide(joke: Joke) {
                dataCallback?.let {
                    joke.map(it)
                }
            }
        })
    }

    fun getJoke() {
        model.getJoke()
    }

    fun clear() {
        dataCallback = null
        model.clear()
    }
}