package com.github.djuise.testrail.api.helpers

import com.fasterxml.jackson.databind.ObjectMapper


fun ObjectMapper.mapToJson(map: Map<String, Any?>): String {
    return objectMapper.writeValueAsString(map)
}

fun <T> ObjectMapper.parse(value: String?, clazz: Class<T>): T {
    return objectMapper.readValue(value, clazz)
}
