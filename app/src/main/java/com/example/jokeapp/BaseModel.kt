package com.example.jokeapp

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

class BaseModel(
    private val jokeService: JokeService,
    private val resourceManager: BaseResourceManager
) : Model {

    private var callback: ResultCallBack? = null
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }


    override fun getJoke() {
        jokeService.getJoke().enqueue(object : retrofit2.Callback<JokeDTO> {
            override fun onResponse(call: Call<JokeDTO>, response: Response<JokeDTO>) {
                if (response.isSuccessful) {
                    callback?.provideSuccess(response.body()!!.toJoke())
                } else {
                    callback?.provideError(serviceUnavailable)
                }
            }

            override fun onFailure(call: Call<JokeDTO>, t: Throwable) {
                if (t is UnknownHostException) {
                    callback?.provideError(noConnection)
                } else {
                    callback?.provideError(serviceUnavailable)
                }
            }
        })
    }

    override fun init(callback: ResultCallBack) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }

}