package com.github.djuise.testrail.api.requests

import com.github.djuise.testrail.api.TestRailRequest
import com.github.djuise.testrail.api.dto.CaseDTO
import com.github.djuise.testrail.api.dto.CasesDTO
import com.github.djuise.testrail.api.dto.UpdatedCasesDTO
import com.github.djuise.testrail.api.helpers.Constants.API2
import com.github.djuise.testrail.api.helpers.jsonMapper
import com.github.djuise.testrail.api.helpers.mapToJsonWithSnakeCase
import com.github.djuise.testrail.api.helpers.objectMapper

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

    fun update(case: CaseDTO): CaseDTO {
        val body = jsonMapper.writeValueAsString(case)
        return TestRailRequest.post("$API2/update_case/${case.id}", body, CaseDTO::class.java)
    }

    fun update(caseId: Int, fields: Map<String, Any?>): CaseDTO {
        val body = objectMapper.mapToJsonWithSnakeCase(fields)
        return TestRailRequest.post("$API2/update_case/$caseId", body, CaseDTO::class.java)
    }

    fun update(casesId: List<Int>, suiteId: Int, fields: Map<String, Any?>): List<CaseDTO> {
        val caseIds = mutableMapOf("caseIds" to casesId)
        val map = fields + caseIds
        val body = objectMapper.mapToJsonWithSnakeCase(map)
        return TestRailRequest.post("$API2/update_cases/$suiteId", body, UpdatedCasesDTO::class.java).updatedCases
    }
}