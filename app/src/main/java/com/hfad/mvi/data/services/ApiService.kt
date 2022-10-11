package com.hfad.mvi.data.services

import com.hfad.mvi.data.User
import com.hfad.mvi.data.Users
import retrofit2.http.GET
import retrofit2.http.Path

// TODO: Запросы

interface ApiService {
    @GET("users")
    suspend fun getUsers():List<Users>

    @GET("users/{login}")
    suspend fun getUser(@Path("login") login: String): User
}