package com.github.djuise.testrail.api

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

 object TestRailRequest {

    val client = OkHttpClient()

    fun get(url: String): Response {
        val request = Request.Builder()
            .url("${Details.url}/$url")
            .header("Authorization", Details.token)
            .build()

        return client.newCall(request).execute()
    }

    // Function to send a POST request to TestRail
    fun post(url: String, jsonBody: String): Response {
        val mediaType = "application/json".toMediaTypeOrNull()
        val body: RequestBody = jsonBody.toRequestBody(mediaType)

        val request = Request.Builder()
            .url("${Details.url}/$url")
            .header("Authorization", Details.token)
            .post(body)
            .build()

        return client.newCall(request).execute()
    }
}