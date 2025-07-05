package com.example.randomstringgenerator.model

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import org.json.JSONObject

class RandomStringRepository(private val context: Context) {

    private val uri = Uri.parse("content://com.iav.contestdataprovider/text")

    fun fetchRandomString(length: Int): Result<StringData> {
        return try {
            val cursor = context.contentResolver.query(
                uri,
                null,
                Bundle().apply { putInt("length", length) },
                null
            )

            Log.d("QueryParam", "Sending length = $length")


            cursor?.use {
                if (it.moveToFirst()) {
                    val jsonString = it.getString(it.getColumnIndexOrThrow("data"))
                    val json = JSONObject(jsonString).getJSONObject("randomText")
                    Result.success(
                        StringData(
                            value = json.getString("value"),
                            length = json.getInt("length"),
                            created = json.getString("created")
                        )
                    )
                } else {
                    Result.failure(Exception("No data"))
                }
            } ?: Result.failure(Exception("Cursor null"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}