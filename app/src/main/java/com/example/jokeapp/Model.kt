package com.example.jokeapp

interface Model {

    fun getJoke()

    fun init(callback: JokeCallback)

    fun clear()

}