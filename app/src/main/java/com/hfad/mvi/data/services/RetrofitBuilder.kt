package com.hfad.mvi.data.services

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {

    // TODO: запрос на сервер

    private const val BASE_URL = "https://api.github.com/"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val api: ApiService = getRetrofit().create(ApiService::class.java)
}