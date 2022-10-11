package com.hfad.mvi.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.mvi.data.repository.UserRepository
import com.hfad.mvi.data.services.ApiHelper
import com.hfad.mvi.viewModel.UserViewModel

class UserModelFactory(private val apiHelper:ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(UserRepository(apiHelper)) as T
        }else{
            throw IllegalArgumentException("error class name")
        }
    }
}