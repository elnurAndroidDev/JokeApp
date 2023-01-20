package com.example.jokeapp

import com.google.gson.annotations.SerializedName

data class JokeDTO(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("type")
    private val type: String,
    @SerializedName("setup")
    private val setup: String,
    @SerializedName("punchline")
    private val punchline: String,
) {
    fun toJoke() = Joke(setup, punchline)
}
