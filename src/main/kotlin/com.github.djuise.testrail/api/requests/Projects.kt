package com.github.djuise.testrail.api.requests

import com.github.djuise.testrail.api.TestRailRequest
import com.github.djuise.testrail.api.dto.ProjectDTO
import com.github.djuise.testrail.api.dto.ProjectsDTO

object Projects {

    fun get(): List<ProjectDTO> {
        return TestRailRequest.get("get_projects", ProjectsDTO::class.java).projects
    }
}