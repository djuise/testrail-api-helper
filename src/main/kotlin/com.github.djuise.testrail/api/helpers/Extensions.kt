package com.github.djuise.testrail.api.helpers

import com.fasterxml.jackson.databind.ObjectMapper


fun ObjectMapper.mapToJsonWithSnakeCase(map: Map<String, Any>): String {
    return objectMapper.writeValueAsString(map).camelToSnake()
}

fun <T> ObjectMapper.parse(value: String?, clazz: Class<T>): T {
    return objectMapper.readValue(value?.snakeToCamel(), clazz)
}

fun String.snakeToCamel(): String {
    return this.split("_")
        .joinToString("") { it.replaceFirstChar { ch -> ch.uppercase() } }
        .replaceFirstChar { it.lowercase() }
}

fun String.camelToSnake(): String {
    return this.replace(Regex("([a-z0-9])([A-Z])"), "$1_$2")
        .lowercase()
}