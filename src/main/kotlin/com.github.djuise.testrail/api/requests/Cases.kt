package com.github.djuise.testrail.api.requests

import com.github.djuise.testrail.api.TestRailRequest
import com.github.djuise.testrail.api.dto.CaseDTO
import com.github.djuise.testrail.api.dto.CasesDTO

object Cases {

    fun getAll(projectId: Int, suiteId: Int): List<CaseDTO> {

        fun getCases(projectId: Int, suiteId: Int, offset: Int, limit: Int): List<CaseDTO> {
            return TestRailRequest.get("get_cases/$projectId&suite_id=$suiteId&limit=$limit&offset=$offset", CasesDTO::class.java).cases
        }

        val allCases = mutableListOf<CaseDTO>()
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
}