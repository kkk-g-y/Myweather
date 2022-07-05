package com.example.myweather.logic.network

import com.example.myweather.MyApplication
import com.example.myweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    /**
     * https://api.caiyunapp.com/v2/place?token=gZO5e8JRV3PNUOaQ&lang=zh_CN&query=wuhan
     * https://api.caiyunapp.com/v2/palce?token=gZO5e8JRV3PNUOaQ&lang=zh_CN&query=wuhan
     */

    @GET("v2/place?token=${MyApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String) : Call<PlaceResponse>
    //Query注解：能够通过GET注解传递参数，"query"是参数名，这样就能向get里面添加 &query={}的内容
    //Call<PlaceResponse>： 是Retrofit会自动将数据解析为PlaceResponse类型
}