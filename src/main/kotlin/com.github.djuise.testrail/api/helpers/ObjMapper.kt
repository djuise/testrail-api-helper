package com.github.djuise.testrail.api.helpers

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.KotlinModule

val objectMapper = ObjectMapper()
    .registerModule(KotlinModule.Builder().build())
//    .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

val jsonMapper = ObjectMapper()
    .registerModule(KotlinModule.Builder().build())
    .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)