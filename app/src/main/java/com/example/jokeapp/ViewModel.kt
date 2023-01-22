package com.example.jokeapp

class ViewModel(private val model: Model) {
    private var callback: DataCallback? = null

    fun init(callback: DataCallback) {
        this.callback = callback
        model.init(object : ResultCallBack {
            override fun provideSuccess(data: Joke) {
                callback.provideText(data.getJokeUI())
                callback.provideIconRes(data.getIconResId())
            }

            override fun provideError(error: JokeFailure) {
                val joke = FailedJoke(error.getMessage())
                callback.provideText(joke.getJokeUI())
                callback.provideIconRes(joke.getIconResId())
            }
        })
    }

    fun getJoke() {
        model.getJoke()
    }

    fun clear() {
        callback = null
        model.clear()
    }
}