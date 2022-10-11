package com.hfad.mvi.data.services

import com.hfad.mvi.data.User
import com.hfad.mvi.data.Users

interface ApiHelper {
    suspend fun getUsers(): List<Users>
    suspend fun getUser(login: String): User
}