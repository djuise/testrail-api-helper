package com.github.djuise.testrail.helpers

import java.io.FileInputStream
import java.util.*

object FileProperties {

    private val properties = Properties().apply { load(FileInputStream("src/test/resources/credentials")) }

    val testRailUrl: String? = properties.getProperty("url")
    val username: String? = properties.getProperty("username")
    val apiToken: String? = properties.getProperty("apiToken")
    val projectId: Int? = properties.getProperty("projectId").toIntOrNull()
    val suiteId: Int? = properties.getProperty("suiteId").toIntOrNull()
}