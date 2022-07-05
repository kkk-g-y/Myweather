package com.example.myweather.logic.network

import android.app.DownloadManager
import com.example.myweather.LogUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {

    private val placeService = ServiceCreator.create<PlaceService>()

    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()
    //placeService.searchPlaces(query)是Call<T>类型的返回数据，await()方法在下面进行了声明

    private suspend fun <T> Call<T>.await(): T{
        return suspendCoroutine { continuation ->
             enqueue(object : Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>){

                    val body = response.body()
//                    LogUtil.d(msg = "网络请求  ${response.toString()}")
//                    LogUtil.d(msg = "网络请求  ${body.toString()}")
                    if(body != null)
                        continuation.resume(body)   //body是方法的返回值，resume会恢复协程，body是服务器响应数据
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }
            })
        }
    }
}