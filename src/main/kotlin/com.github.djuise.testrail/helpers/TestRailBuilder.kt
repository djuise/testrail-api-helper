package com.github.djuise.testrail.helpers

import com.github.djuise.testrail.api.dto.CaseDTO
import com.github.djuise.testrail.api.dto.ProjectDTO
import com.github.djuise.testrail.api.dto.SectionDTO
import com.github.djuise.testrail.api.dto.SuiteDTO
import com.github.djuise.testrail.api.helpers.ProjectId

interface TCredential {
    fun username(username: String): TPassword
}

interface TPassword {
    fun password(password: String): TestRailFunctions
    fun apiToken(apiToken: String): TestRailFunctions
}

interface TestRailFunctions {
    fun getProjects(): List<ProjectDTO>
    fun getSuites(projectId: Int): List<SuiteDTO>
    fun getFirstFoundSuiteIdByName(name: String, projectId: Int): Int?
    fun getFirstFoundProjectIdByName(name: String): Int?
    fun getCases(projectId: Int, suiteId: Int): List<CaseDTO>
    fun createRun(name: String): ProjectId
    fun getSections(projectId: Int, suiteId: Int): List<SectionDTO>
    fun getSectionsWithChilds(projectId: Int, suiteId: Int, sectionsId: List<Int>): List<SectionDTO>
    fun getSectionWithChilds(projectId: Int, suiteId: Int, sectionsId: Int): List<SectionDTO>
}