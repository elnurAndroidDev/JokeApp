package com.example.jokeapp

import com.google.gson.annotations.SerializedName

data class JokeServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("type")
    private val type: String,
    @SerializedName("setup")
    private val setup: String,
    @SerializedName("punchline")
    private val punchline: String,
) {
    fun toJoke() = BaseJoke(setup, punchline)

    fun change(cashDataSource: CacheDataSource) = cashDataSource.addOrRemove(id, this)
}
