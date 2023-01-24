package com.example.jokeapp

interface CacheDataSource {
    fun addOrRemove(id: Int, joke: JokeServerModel) : Joke
}