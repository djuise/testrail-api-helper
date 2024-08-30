package com.github.djuise.testrail.api

import com.github.djuise.testrail.TestRail
import com.github.djuise.testrail.api.helpers.Constants.API2
import com.github.djuise.testrail.api.helpers.objectMapper
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response

object TestRailRequest {

    private val client = OkHttpClient()
    private val requestBuilder = Request.Builder()
        .header("Authorization", "Basic ${TestRail.token}")

    fun get(baseUrl: String): Response {
        val request = requestBuilder
            .url("${TestRail.baseUrl}/$baseUrl")
            .build()

        return client.newCall(request).execute()
    }

    fun <T>get(path: String, clazz: Class<T>): T {
        val response = get("$API2/$path")
        val stringBody = response.body?.string()
        return objectMapper.readValue(stringBody, clazz)
    }

    fun post(baseUrl: String, jsonBody: String): Response {
        val mediaType = "application/json".toMediaTypeOrNull()
        val body: RequestBody = jsonBody.toRequestBody(mediaType)

        val request = requestBuilder
            .url("${TestRail.baseUrl}/$baseUrl")
            .post(body)
            .build()

        return client.newCall(request).execute()
    }
}