package com.example.jokeapp

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

class BaseCloudDataSource(private val jokeService: JokeService) : CloudDataSource {

    override fun getJoke(callback: JokeCloudCallback) {
        jokeService.getJoke().enqueue(object : retrofit2.Callback<JokeServerModel> {
            override fun onResponse(
                call: Call<JokeServerModel>,
                response: Response<JokeServerModel>
            ) {
                if (response.isSuccessful) {
                    callback.provide(response.body()!!)
                } else {
                    callback.fail(ErrorType.SERVICE_UNAVAILABLE)
                }
            }

            override fun onFailure(call: Call<JokeServerModel>, t: Throwable) {
                if (t is UnknownHostException) {
                    callback.fail(ErrorType.NO_CONNECTION)
                } else {
                    callback.fail(ErrorType.SERVICE_UNAVAILABLE)
                }
            }
        })
    }
}