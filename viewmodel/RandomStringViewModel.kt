package com.example.randomstringgenerator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomstringgenerator.model.RandomStringRepository
import com.example.randomstringgenerator.model.StringData
import kotlinx.coroutines.launch

class RandomStringViewModel(private val repository: RandomStringRepository): ViewModel() {

    var strings by mutableStateOf<List<StringData>>(emptyList())
        private set

    var error by mutableStateOf<String?>(null)
        private set

    fun generateString(length: Int) {
        viewModelScope.launch {
            val result = repository.fetchRandomString(length)
            result.onSuccess {
                strings = strings + it

                if (it.length != length) {
                    error = "Warning: As per input expected length is $length but the output from content provider i ${it.length}"
                }
            }.onFailure {
                error = it.message
            }
        }
    }

    fun clearAll() {
        strings = emptyList()
        error = null
    }

    fun deleteItem(item: StringData) {
        strings = strings.filterNot { it == item }
        error = null
    }
}