package com.hfad.mvi.data.repository

import com.hfad.mvi.data.services.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}
