package com.example.tomo.yamaapp.model.webapi

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by tomo on 2018/04/06.
 */
object WebApiClient {

    val retrofit: Retrofit by lazy { createRetrofit() }

    private fun createRetrofit(): Retrofit {

        val clientBuilder = OkHttpClient().newBuilder()
        clientBuilder.addInterceptor {
            it.proceed(it.request()
                    .newBuilder()
                    .build())
        }

        return Retrofit.Builder()
                .baseUrl("https://s3-ap-northeast-1.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(clientBuilder.build())
                .build()
    }
}