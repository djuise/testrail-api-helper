package com.github.djuise.testrail.helpers

import com.github.djuise.testrail.api.dto.CaseDTO
import com.github.djuise.testrail.api.dto.ProjectDTO
import com.github.djuise.testrail.api.dto.SectionDTO
import com.github.djuise.testrail.api.dto.SuiteDTO
import com.github.djuise.testrail.api.helpers.ProjectId
import com.github.djuise.testrail.api.helpers.SuiteId
import com.github.djuise.testrail.api.helpers.TestRunFunctions

interface TUsername {
    /**
     * Sets the username for API authentication.
     *
     * @param username The username used for authentication.
     * @return Returns an instance of TPassword to continue the authentication process.
     */
    fun username(username: String): TPassword
}

interface TPassword {
    /**
     * Sets the password for API authentication and encodes credentials.
     *
     * @param password The password used for authentication.
     * @return Returns an instance of TestRailFunctions allowing access to API functions.
     */
    fun password(password: String): TestRailFunctions

    /**
     * Sets the API token for authentication and encodes it.
     *
     * @param apiToken The API token provided by TestRail.
     * @return Returns an instance of TestRailFunctions allowing access to API functions.
     */
    fun apiToken(apiToken: String): TestRailFunctions
}

interface TestRailFunctions: Functions {
    /**
     * Sets the project ID for using functions without projectId parameter
     *
     * @param id The TestRail project ID
     * @return Returns an instance of TestRailFunctions allowing access to API functions.
     */
    fun projectId(id: Int): TestRailFunctionsForConfiguredProject

    /**
     * Retrieves a list of test suites associated with a given project ID.
     *
     * @param projectId The ID of the project.
     * @return Returns a list of Suite objects.
     */
    fun getSuites(projectId: Int): List<SuiteDTO>

    /**
     * Searches for the first suite by name within a specific project and returns its ID.
     *
     * @param name The name of the suite to search for.
     * @param projectId The ID of the project that the suite belongs to.
     * @return Returns the ID of the first found suite or null if no match is found.
     */
    fun getFirstFoundSuiteIdByName(name: String, projectId: Int): Int?

    /**
     * Retrieves all cases for a given project and suite ID.
     *
     * @param projectId The project ID.
     * @param suiteId The suite ID within the project.
     * @return Returns a List<CaseDTO>
     */
    fun getCases(projectId: Int, suiteId: Int): List<CaseDTO>

    /**
     * Get sections for a suite
     *
     * @param projectId The ID of the project that the suite belongs to.
     * @param suiteId The suite ID within the project.
     * @return Returns a List<SectionDTO>.
     */
    fun getSections(projectId: Int, suiteId: Int): List<SectionDTO>

    /**
     * Get sections with their childs for a suite
     *
     * @param projectId The ID of the project that the suite belongs to.
     * @param suiteId The suite ID within the project.
     * @param sectionsId The parent section ids
     * @return Returns a List<SectionDTO>.
     */
    fun getSectionsWithChildren(projectId: Int, suiteId: Int, sectionsId: List<Int>): List<SectionDTO>

    /**
     * Get sections with their childs for a suite
     *
     * @param projectId The ID of the project that the suite belongs to.
     * @param suiteId The suite ID within the project.
     * @param sectionId The parent section id
     * @return Returns a List<SectionDTO>.
     */
    fun getSectionWithChildren(projectId: Int, suiteId: Int, sectionId: Int): List<SectionDTO>

    /**
     * Get sections parent for child with id
     *
     * @param projectId The ID of the project that the suite belongs to.
     * @param suiteId The suite ID within the project.
     * @param sectionId The child section id
     * @return Returns a SectionDTO.
     */
    fun getMainSectionForChild(projectId: Int, suiteId: Int, sectionId: Int): SectionDTO?

    /**
     * Creates a new test run TestRail run builder within TestRail.
     *
     * @param name The name of the new test run.
     * @return Returns a TestRailRunBuilder.
     */
    fun createRun(name: String): ProjectId
}

interface TestRailFunctionsForConfiguredProject: Functions, ConfiguredProjectFunctions {
    fun suiteId(id: Int): TestRailFunctionsForConfiguredProjectAndSuite

    /**
     * Retrieves all cases for a given project and suite ID.
     *
     * @param suiteId The suite ID within the project.
     * @return Returns a List<CaseDTO>
     */
    fun getCases(suiteId: Int): List<CaseDTO>

    /**
     * Get sections for a suite
     *
     * @param suiteId The suite ID within the project.
     * @return Returns a List<SectionDTO>.
     */
    fun getSections(suiteId: Int): List<SectionDTO>

    /**
     * Get sections with their childs for a suite
     *
     * @param suiteId The suite ID within the project.
     * @param sectionsId The parent section ids
     * @return Returns a List<SectionDTO>.
     */
    fun getSectionsWithChildren(suiteId: Int, sectionsId: List<Int>): List<SectionDTO>

    /**
     * Get sections with their childs for a suite
     *
     * @param suiteId The suite ID within the project.
     * @return Returns a List<SectionDTO>.
     */
    fun getSectionWithChildren(suiteId: Int, sectionId: Int): List<SectionDTO>

    /**
     * Get sections parent for child with id
     *
     * @param suiteId The suite ID within the project.
     * @param sectionId The child section id
     * @return Returns a SectionDTO.
     */
    fun getMainSectionForChild(suiteId: Int, sectionId: Int): SectionDTO?

    /**
     * Creates a new test run TestRail run builder within TestRail.
     *
     * @param name The name of the new test run.
     * @return Returns a TestRailRunBuilder.
     */
    fun createRunForCurrentProject(name: String): SuiteId
}

interface TestRailFunctionsForConfiguredProjectAndSuite: Functions, ConfiguredProjectFunctions {
    /**
     * Retrieves all cases for a given project and suite ID.
     *
     * @return Returns a List<CaseDTO>
     */
    fun getCases(): List<CaseDTO>

    /**
     * Get sections for a suite
     *
     * @return Returns a List<SectionDTO>.
     */
    fun getSections(): List<SectionDTO>

    /**
     * Get sections with their childs for a suite
     *
     * @param sectionsId The parent section ids
     * @return Returns a List<SectionDTO>.
     */
    fun getSectionsWithChildren(sectionsId: List<Int>): List<SectionDTO>

    /**
     * Get sections with their childs for a suite
     *
     * @param sectionId The parent section id
     * @return Returns a List<SectionDTO>.
     */
    fun getSectionWithChildren(sectionId: Int): List<SectionDTO>

    /**
     * Get sections parent for child with id
     *
     * @param sectionId The child section id
     * @return Returns a SectionDTO.
     */
    fun getMainSectionForChild(sectionId: Int): SectionDTO?

    /**
     * Creates a new test run TestRail run builder within TestRail.
     *
     * @param name The name of the new test run.
     * @return Returns a TestRailRunBuilder.
     */
    fun createRunForCurrentProjectAndSuite(name: String): TestRunFunctions

    /**
     * Update test case
     *
     * @param id The Test Cases IDs
     * @param suiteId The Suite ID
     * @param fields The map with updated fields
     * @return Returns a List<CaseDTO>.
     */
    fun updateTestCases(id: List<Int>, fields: Map<String, Any?>): List<CaseDTO>
}

interface Functions {

    /**
     * Retrieves a list of all projects from TestRail.
     *
     * @return Returns a List<ProjectDTO>
     */
    fun getProjects(): List<ProjectDTO>

    /**
     * Update test case
     *
     * @param id The Test Case ID
     * @param fields The map with updated fields
     * @return Returns a CaseDTO.
     */
    fun updateTestCase(id: Int, fields: Map<String, Any?>): CaseDTO

    /**
     * Update test case
     *
     * @param id The Test Cases IDs
     * @param suiteId The Suite ID
     * @param fields The map with updated fields
     * @return Returns a List<CaseDTO>.
     */
    fun updateTestCases(id: List<Int>, suiteId: Int, fields: Map<String, Any?>): List<CaseDTO>

    /**
     * Update test case
     *
     * @param case The Test Case (CaseDTO)
     * @return Returns a CaseDTO.
     */
    fun updateTestCase(case: CaseDTO): CaseDTO

    /**
     * Searches for the first project by name and returns its ID.
     *
     * @param name The name of the project to search for.
     * @return Returns the ID of the first found project or null if no match is found.
     */
    fun getFirstFoundProjectIdByName(name: String): Int?
}

interface ConfiguredProjectFunctions {
    /**
     * Retrieves a list of test suites associated with a given project ID.
     *
     * @return Returns a list of Suite objects.
     */
    fun getSuites(): List<SuiteDTO>

    /**
     * Searches for the first suite by name within a specific project and returns its ID.
     *
     * @param name The name of the suite to search for.
     * @return Returns the ID of the first found suite or null if no match is found.
     */
    fun getFirstFoundSuiteIdByName(name: String): Int?
}