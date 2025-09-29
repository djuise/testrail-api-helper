package com.github.djuise.testrail.api.requests

import com.github.djuise.testrail.api.TestRailApiClient
import com.github.djuise.testrail.api.dto.ProjectDTO
import com.github.djuise.testrail.api.helpers.call

object Projects {

    fun get(): Set<ProjectDTO> {
        return TestRailApiClient.api.getProjects().call().projects
    }
}