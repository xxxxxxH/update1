package net.basicmodel

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface AppService {

    @POST("config")
    suspend fun getConfig(): ResponseBody?

    @POST
    fun uploadFbData(
        @Url url: String,
        @Body body: Map<String, String>,
    ): ResultPojo?
}