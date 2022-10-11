package com.hfad.mvi.data.repository

import com.hfad.mvi.data.services.ApiHelper

class UserRepository(private val apiHelper: ApiHelper) {
    suspend fun getUser(login: String) = apiHelper.getUser(login)
}