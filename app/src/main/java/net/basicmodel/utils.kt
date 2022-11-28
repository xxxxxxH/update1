package net.basicmodel

import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val url = "https://kcoffni.xyz/api/open/collect"
val v = "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"

 fun clientCreator(block: OkHttpClient.Builder.() -> OkHttpClient.Builder = { this }) =
    OkHttpClient.Builder()
        .readTimeout(15000, TimeUnit.MILLISECONDS)
        .writeTimeout(15000, TimeUnit.MILLISECONDS)
        .connectTimeout(15000, TimeUnit.MILLISECONDS)
        .block()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

 fun devkitServiceCreator() =
    Retrofit
        .Builder()
        .client(clientCreator())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("")
        .build()