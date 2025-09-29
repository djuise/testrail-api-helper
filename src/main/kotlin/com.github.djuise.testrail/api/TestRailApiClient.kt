package com.github.djuise.testrail.api

import com.github.djuise.testrail.TestRail
import com.github.djuise.testrail.api.helpers.objectMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object TestRailApiClient {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val withAuth = original.newBuilder()
                .header("Authorization", "Basic ${TestRail.token}")
                .header("Accept", "application/json")
                .build()
            chain.proceed(withAuth)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(TestRail.baseUrl)
        .client(okHttpClient)
        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
        .build()

    val api: TestRailApi = retrofit.create(TestRailApi::class.java)
}
