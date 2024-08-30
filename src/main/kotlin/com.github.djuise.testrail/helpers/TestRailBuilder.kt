package com.github.djuise.testrail.helpers

import com.github.djuise.testrail.api.dto.CasesDTO
import com.github.djuise.testrail.api.dto.ProjectsDTO
import com.github.djuise.testrail.api.dto.Suite
import com.github.djuise.testrail.api.helpers.ProjectId

interface TCredential {
    fun username(username: String): TPassword
}

interface TPassword {
    fun password(password: String): TestRailFunctions
    fun apiToken(apiToken: String): TestRailFunctions
}

interface TestRailFunctions {
    fun getProjects(): ProjectsDTO
    fun getSuites(projectId: Int): List<Suite>
    fun getFirstFoundSuiteIdByName(name: String, projectId: Int): Int?
    fun getFirstFoundProjectIdByName(name: String): Int?
    fun getCases(projectId: Int, suiteId: Int): CasesDTO
    fun createRun(name: String): ProjectId
}