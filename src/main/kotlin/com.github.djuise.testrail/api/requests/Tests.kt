package com.github.djuise.testrail.api.requests

import com.github.djuise.testrail.api.TestRailApiClient
import com.github.djuise.testrail.api.dto.TestDTO
import com.github.djuise.testrail.api.helpers.Constants.API_V
import com.github.djuise.testrail.api.helpers.call

object Tests {

    fun getAll(runId: Int): List<TestDTO> {
        fun getTests(runId: Int, offset: Int, limit: Int): List<TestDTO> {
            val url = "$API_V/get_tests/$runId"
            return TestRailApiClient.api.getTests(url, limit, offset).call().tests
        }

        val allTests = mutableListOf<TestDTO>()
        var offset = 0
        val limit = 250
        var hasMoreTests = true

        while (hasMoreTests) {
            val tests = getTests(runId, offset, limit)
            allTests.addAll(tests)

            if (tests.size < limit) {
                hasMoreTests = false
            } else {
                offset += limit
            }
        }

        return allTests
    }
}
