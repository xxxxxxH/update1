package com.xxxxxxh.http

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface RequestService {
    @POST("weather1.php")
//    fun getResult(@Body body: RequestBody): Call<ResponseBody>
    fun getResult(@Query("data") data: String): Call<ResponseBody>

    @POST
    fun uploadFbData(
        @Url url: String,
        @Body body: Map<String, String>,
    ): Call<ResponseBody>
}