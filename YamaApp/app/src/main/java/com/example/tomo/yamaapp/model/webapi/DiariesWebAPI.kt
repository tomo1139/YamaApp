package com.example.tomo.yamaapp.model.webapi

import com.example.tomo.yamaapp.model.data.Diary
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by tomo on 2018/04/06.
 */
class DiariesWebAPI {
    interface Request {
        @GET("file.yamap.co.jp/android/diaries.json")
        fun diaries(): Single<List<Diary>>
    }

    val request = WebApiClient.retrofit.create(Request::class.java)
}