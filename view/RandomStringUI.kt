package com.example.randomstringgenerator.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.randomstringgenerator.viewmodel.RandomStringViewModel

@Composable
fun RandomStringUI(viewModel: RandomStringViewModel){

    var inputLength by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = inputLength,
            onValueChange = { inputLength = it },
            label = { Text("String Length") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(onClick = {
            inputLength.toIntOrNull()?.let {
                viewModel.generateString(it)
            }
        }) {
            Text("Generate")
        }

        viewModel.error?.let {
            Text("Error: $it", color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }

        Button(
            onClick = { viewModel.clearAll() },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Clear All")
        }

        LazyColumn {
            items(viewModel.strings) { item ->
                val jsonFormatted = """
                {
                    "randomText": {
                    "value": "${item.value}",
                    "length": ${item.length},
                    "created": "${item.created}"
                }
                }
                """.trimIndent()

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
//                        Text("Random Text: ${item.value}")
//                        Text("Length: ${item.length}")
//                        Text("Created: ${item.created}")
                        Text(jsonFormatted)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.deleteItem(item) }) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}