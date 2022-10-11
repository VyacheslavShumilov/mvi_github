package com.hfad.mvi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.mvi.data.repository.UserRepository
import com.hfad.mvi.intent.UserIntent
import com.hfad.mvi.viewState.UserState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository): ViewModel() {
    val userIntent = Channel<UserIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<UserState>(UserState.Id)

    val state: StateFlow<UserState>
    get() = _state

    // TODO: Получение User
    fun handlerIntent(login:String) {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect{
                when(it) {
                    is UserIntent.LoadUser -> fetchUser(login)
                }
            }
        }
    }

    private fun fetchUser(login: String){
        viewModelScope.launch {
            _state.value = UserState.Loading
            _state.value = try {
                UserState.UserOne(repository.getUser(login))
            } catch (e: Exception) {
                UserState.Error(e.localizedMessage)
            }
        }
    }
}