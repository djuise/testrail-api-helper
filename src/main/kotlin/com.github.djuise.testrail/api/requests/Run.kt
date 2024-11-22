package com.github.djuise.testrail.api.requests

import com.github.djuise.testrail.api.TestRailRequest
import com.github.djuise.testrail.api.dto.CreateRunDTO
import com.github.djuise.testrail.api.dto.RunDTO
import com.github.djuise.testrail.api.helpers.Constants.API2
import com.github.djuise.testrail.api.helpers.objectMapper

object Run {

    fun create(projectId: Int, run: CreateRunDTO): Int {
        val runJson = objectMapper.writeValueAsString(run)
        val response = TestRailRequest.post("$API2/add_run/$projectId", runJson, RunDTO::class.java)

        return response.id
    }
}