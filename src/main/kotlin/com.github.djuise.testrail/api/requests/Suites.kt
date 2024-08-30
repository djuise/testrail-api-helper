package com.github.djuise.testrail.api.requests

import com.fasterxml.jackson.core.type.TypeReference
import com.github.djuise.testrail.api.TestRailRequest
import com.github.djuise.testrail.api.dto.Suite
import com.github.djuise.testrail.api.helpers.Constants.API2
import com.github.djuise.testrail.api.helpers.objectMapper

object Suites {

    fun get(projectId: Int): List<Suite> {
        val response = TestRailRequest.get("$API2/get_suites/$projectId/")
        val stringBody = response.body?.string()
        return objectMapper.readValue(objectMapper.createParser(stringBody), object : TypeReference<List<Suite>>() {})
    }
}