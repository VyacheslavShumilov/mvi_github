package com.hfad.mvi.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.mvi.data.repository.MainRepository
import com.hfad.mvi.data.services.ApiHelper
import com.hfad.mvi.viewModel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(MainViewModel::class.java)){
           return MainViewModel(MainRepository(apiHelper)) as T
       }else{
           throw IllegalArgumentException("on error class name")
       }
    }
}