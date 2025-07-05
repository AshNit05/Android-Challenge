package com.example.randomstringgenerator.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randomstringgenerator.model.RandomStringRepository

class RandomStringViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RandomStringViewModel(RandomStringRepository(context)) as T
    }
}