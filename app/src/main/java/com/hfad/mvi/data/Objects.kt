package com.hfad.mvi.data

import com.squareup.moshi.Json

data class Users(
    @Json(name = "id") val id: Int,
    @Json(name = "login") val login: String,
    @Json(name = "avatar_url") val avatar_url: String
)

data class User(
    @Json(name = "login") val login: String,
    @Json(name = "id") val id: Int,
    @Json(name = "node_id") val node_id: String,
    @Json(name = "avatar_url") val avatar_url: String,
    @Json(name = "followers_url") val followers_url: String,
    @Json(name = "type") val type: String
)