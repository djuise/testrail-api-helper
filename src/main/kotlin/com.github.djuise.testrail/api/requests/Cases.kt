package com.github.djuise.testrail.api.requests

import com.github.djuise.testrail.api.TestRailApiClient
import com.github.djuise.testrail.api.dto.CaseDTO
import com.github.djuise.testrail.api.helpers.Constants.API_V
import com.github.djuise.testrail.api.helpers.call

object Cases {

    fun getAll(projectId: Int, suiteId: Int): Set<CaseDTO> {

        fun getCases(projectId: Int, suiteId: Int, offset: Int, limit: Int): Set<CaseDTO> {
            val url = "$API_V/get_cases/$projectId"
            return TestRailApiClient.api.getCases(url, suiteId, limit, offset).call().cases
        }

        val allCases = mutableSetOf<CaseDTO>()
        var offset = 0
        val limit = 250
        var hasMoreCases = true

        while (hasMoreCases) {
            val cases = getCases(projectId, suiteId, offset, limit)
            allCases.addAll(cases)

            if (cases.size < limit) {
                hasMoreCases = false
            } else {
                offset += limit
            }
        }

        return allCases
    }

    fun getCase(id: Int): CaseDTO {
        val url = "$API_V/get_case/$id"
        return TestRailApiClient.api.getCase(url).call()
//        val call = TestRailApiClient.api.getCase(url)
    }

    fun update(case: CaseDTO): CaseDTO {
        val url = "$API_V/update_case/${case.id}"
        return TestRailApiClient.api.updateCase(url, case).call()
    }

    fun update(caseId: Int, fields: Map<String, Any?>): CaseDTO {
        val url = "$API_V/update_case/$caseId"
        return TestRailApiClient.api.updateCaseFields(url, fields).call()
    }

    fun update(casesId: List<Int>, suiteId: Int, fields: Map<String, Any?>): Set<CaseDTO> {
        val caseIds = mutableMapOf("caseIds" to casesId)
        val map = fields + caseIds
        val url = "$API_V/update_cases/$suiteId"
        return TestRailApiClient.api.updateCases(url, map).call().updatedCases
    }
}