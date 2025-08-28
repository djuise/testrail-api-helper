package com.github.djuise.testrail.api.requests

import com.fasterxml.jackson.core.type.TypeReference
import com.github.djuise.testrail.api.TestRailRequest
import com.github.djuise.testrail.api.dto.SuiteDTO
import com.github.djuise.testrail.api.helpers.Constants.API2
import com.github.djuise.testrail.api.helpers.objectMapper
import java.io.IOException

object Suites {

    fun get(projectId: Int): Set<SuiteDTO> {
        val response = TestRailRequest.get("$API2/get_suites/$projectId/")

        return response.use { resp ->
            if (!resp.isSuccessful) {
                throw IOException("Response code: ${resp.code}\nBody: ${resp.body?.string()}")
            }
            val stringBody = response.body?.string()
            objectMapper.readValue(objectMapper.createParser(stringBody), object : TypeReference<Set<SuiteDTO>>() {})
        }
    }
}