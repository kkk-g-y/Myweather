package com.example.myweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private const val BASE_URL = "https://api.caiyunapp.com/"

    /**
     * https://api.caiyunapp.com/v2/place?token=gZO5e8JRV3PNUOaQ&lang=zh_CN&query=wuhan
     * https://api.caiyunapp.com/v2/palce?token=gZO5e8JRV3PNUOaQ&lang=zh_CN&query=wuhan
     */
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create() : T = create(T::class.java)
    //这里进行了泛型实化reified，所以方法传入的参数里面不能带泛型，然后后面的create调用上面的create(serviceClass: Class<T>): T
}