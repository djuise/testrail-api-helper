package com.github.djuise.testrail.api.requests

import com.github.djuise.testrail.api.TestRailApiClient
import com.github.djuise.testrail.api.dto.CreateRunDTO
import com.github.djuise.testrail.api.dto.RunDTO
import com.github.djuise.testrail.api.helpers.Constants.API_V
import com.github.djuise.testrail.api.helpers.call

object Run {

    fun get(runId: Int): RunDTO {
        val url = "$API_V/get_run/$runId"
        return TestRailApiClient.api.getRun(url).call()
    }

    fun create(projectId: Int, run: CreateRunDTO): RunDTO {
        val url = "$API_V/add_run/$projectId"
        return TestRailApiClient.api.createRun(url, run).call()
    }

    fun update(runId: Int, run: RunDTO): RunDTO {
        val url = "$API_V/update_run/$runId"
        return TestRailApiClient.api.updateRun(url, run).call()
    }

    fun close(runId: Int): RunDTO {
        val url = "$API_V/close_run/$runId"
        return TestRailApiClient.api.closeRun(url).call()
    }

    fun delete(runId: Int) {
        val url = "$API_V/delete_run/$runId"
        TestRailApiClient.api.deleteRun(url).call()
    }
}