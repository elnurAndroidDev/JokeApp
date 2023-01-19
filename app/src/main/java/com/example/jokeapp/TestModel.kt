package com.example.jokeapp

import kotlin.concurrent.thread

class TestModel(resourceManager: ResourceManager) : Model {

    private var callback: ResultCallBack? = null
    private val noConnection = NoConnection(resourceManager)
    private val serviceUnavailable = ServiceUnavailable(resourceManager)
    private var count = 0

    override fun getJoke() {
        thread {
            Thread.sleep(1000)
            when (count) {
                0 -> callback?.provideSuccess(Joke("joke", "punchline"))
                1 -> callback?.provideError(serviceUnavailable)
                2 -> callback?.provideError(noConnection)
            }
            count++
            if (count == 3) count = 0
        }
    }

    override fun init(callback: ResultCallBack) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }

}