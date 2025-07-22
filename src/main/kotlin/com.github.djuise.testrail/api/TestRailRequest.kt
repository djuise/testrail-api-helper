package com.github.djuise.testrail.api

import com.github.djuise.testrail.TestRail
import com.github.djuise.testrail.api.helpers.Constants.API2
import com.github.djuise.testrail.api.helpers.objectMapper
import com.github.djuise.testrail.api.helpers.parse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

object TestRailRequest {

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val withAuth = original.newBuilder()
                .header("Authorization", "Basic ${TestRail.token}")
                .header("Accept", "application/json")
                .build()
            chain.proceed(withAuth)
        }
        .build()

    private val requestBuilder
        get() = Request.Builder()

    fun get(baseUrl: String): Response {
        val request = requestBuilder
            .url("${TestRail.baseUrl}/$baseUrl")
            .get()
            .build()

        return client.newCall(request).execute()
    }

    fun <T> get(path: String, clazz: Class<T>): T {
        val response = get("$API2/$path")
        return response.use { resp ->
            if (!resp.isSuccessful) {
                throw IOException("Response code: ${resp.code}\nBody: ${resp.body?.string()}")
            }
            val bodyString = resp.body?.string()
            objectMapper.parse(bodyString, clazz)
        }
    }

    fun <T>post(baseUrl: String, jsonBody: String, clazz: Class<T>): T {
        val response = post(baseUrl, jsonBody)

        return response.use { resp ->
            if (!resp.isSuccessful) {
                throw IOException("Response code: ${resp.code}\nBody: ${resp.body?.string()}")
            }
            val bodyString = resp.body?.string()
            objectMapper.parse(bodyString, clazz)
        }
    }

//    fun <T>post(baseUrl: String, jsonBody: String, clazz: Class<T>): T {
//        val response = post(baseUrl, jsonBody)
//
//        if (response.code !in 200..<300)
//            throw Exception("""Something went wrong.
//                |Status code: ${response.code}
//                |Error: ${response.body?.string()}""".trimMargin())
//
//        return objectMapper.parse(response.body?.string(), clazz)
//    }

    private fun post(baseUrl: String, jsonBody: String): Response {
        val mediaType = "application/json".toMediaTypeOrNull()
        val body: RequestBody = jsonBody.toRequestBody(mediaType)

        val request = requestBuilder
            .url("${TestRail.baseUrl}/$baseUrl")
            .post(body)
            .build()

        return client.newCall(request).execute()
    }
}