package com.github.djuise.testrail.api.requests

import com.github.djuise.testrail.api.TestRailRequest
import com.github.djuise.testrail.api.dto.CaseDTO
import com.github.djuise.testrail.api.dto.CasesDTO

object Cases {

    fun getAll(projectId: Int, suiteId: Int): CasesDTO {

        fun getCases(projectId: Int, suiteId: Int, offset: Int, limit: Int): CasesDTO {
            return TestRailRequest.get("get_cases/$projectId&suite_id=$suiteId&limit=$limit&offset=$offset", CasesDTO::class.java)
        }

        val allCases = mutableListOf<CaseDTO>()
        var offset = 0
        val limit = 250
        var hasMoreCases = true

        while (hasMoreCases) {
            val casesResponse = getCases(projectId, suiteId, offset, limit)
            allCases.addAll(casesResponse.cases)

            if (casesResponse.cases.size < limit) {
                hasMoreCases = false
            } else {
                offset += limit
            }
        }

        return CasesDTO(allCases)
    }
}