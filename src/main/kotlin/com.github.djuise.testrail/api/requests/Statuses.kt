package com.github.djuise.testrail.api.requests

import com.github.djuise.testrail.api.TestRailApiClient
import com.github.djuise.testrail.api.dto.StatusDTO
import com.github.djuise.testrail.api.helpers.call

object Statuses {

    fun getAll(): List<StatusDTO> =
        TestRailApiClient.api.getStatuses().call()
}
