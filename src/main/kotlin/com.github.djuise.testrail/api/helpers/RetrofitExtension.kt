package com.github.djuise.testrail.api.helpers

import retrofit2.Call
import java.io.IOException

/**
 * Executes a Retrofit call and handles the response
 */
fun <T>Call<T>.call(): T {
    val response = this.execute()
    if (!response.isSuccessful) {
        throw IOException("Response code: ${response.code()}\nBody: ${response.errorBody()?.string()}")
    }
    return response.body() ?: throw IOException("Response body is null")
}