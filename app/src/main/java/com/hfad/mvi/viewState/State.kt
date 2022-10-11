package com.hfad.mvi.viewState

import com.hfad.mvi.data.User
import com.hfad.mvi.data.Users

sealed class MainState {
    object Id : MainState()
    object Loading : MainState()
    data class UsersList(val users: List<Users>) : MainState()
    data class Error(val error: String?) : MainState()
}

sealed class UserState {
    object Id : UserState()
    object Loading : UserState()
    data class UserOne(val user: User) : UserState()
    data class Error(val error: String?) : UserState()
}