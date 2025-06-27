package com.github.djuise.testrail.helpers

import java.io.FileInputStream
import java.util.*

object FileProperties {

    private val properties = Properties().apply { load(FileInputStream("src/test/resources/credentials")) }

    val testRailUrl = properties.getProperty("url")
    val username = properties.getProperty("username")
    val apiToken = properties.getProperty("apiToken")
    val projectId = properties.getProperty("projectId").toInt()
    val suiteId = properties.getProperty("suiteId").toInt()
}