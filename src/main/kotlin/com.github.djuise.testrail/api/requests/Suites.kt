package com.github.djuise.testrail.api.requests

import com.github.djuise.testrail.api.TestRailApiClient
import com.github.djuise.testrail.api.dto.SuiteDTO
import com.github.djuise.testrail.api.helpers.Constants.API_V
import com.github.djuise.testrail.api.helpers.call

object Suites {

    fun get(projectId: Int): Set<SuiteDTO> {
        val url = "$API_V/get_suites/$projectId"
        return TestRailApiClient.api.getSuites(url).call()
    }
}