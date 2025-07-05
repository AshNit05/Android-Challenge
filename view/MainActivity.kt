package com.example.randomstringgenerator.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.randomstringgenerator.viewmodel.RandomStringViewModel
import com.example.randomstringgenerator.viewmodel.RandomStringViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: RandomStringViewModel = viewModel(
                factory = RandomStringViewModelFactory(applicationContext)
            )

            androidx.compose.material3.MaterialTheme {
                RandomStringUI(viewModel)
            }
        }
    }
}