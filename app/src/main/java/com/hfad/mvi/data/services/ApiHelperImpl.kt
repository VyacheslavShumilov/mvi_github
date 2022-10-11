package com.hfad.mvi.data.services

import com.hfad.mvi.data.User
import com.hfad.mvi.data.Users

// TODO: Связка ApiHelper и ApiService

class ApiHelperImpl(private val apiService: ApiService):ApiHelper {
    override suspend fun getUsers(): List<Users> {
        return apiService.getUsers()
    }

    override suspend fun getUser(login: String): User {
        return apiService.getUser(login)
    }
}