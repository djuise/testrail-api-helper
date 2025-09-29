package com.github.djuise.testrail

import com.github.djuise.testrail.api.dto.*
import com.github.djuise.testrail.api.helpers.ProjectId
import com.github.djuise.testrail.api.helpers.SuiteId
import com.github.djuise.testrail.api.helpers.TestRailRunBuilder
import com.github.djuise.testrail.api.helpers.TestRunFunctions
import com.github.djuise.testrail.api.requests.*
import com.github.djuise.testrail.helpers.*
import java.util.*

/**
 * The `TestRail` class provides an interface to interact with the TestRail API.
 * It supports authentication via username and password or API token, and offers methods to manage projects, test suites, and test cases.
 */

class TestRail private constructor(): TUsername,
    TPassword,
    TestRailFunctions,
    TestRailFunctionsForConfiguredProject,
    TestRailFunctionsForConfiguredProjectAndSuite {

    companion object {
        /** The base URL of the TestRail server. */
        lateinit var baseUrl: String

        /** The authentication token used for API requests. Generated automatically depends on provided credentials. */
        lateinit var token: String

        /** The project id with client works. Sets in builder or can be empty */
        var projectId: Int? = null

        /** The suite id with client works. Sets in builder or can be empty */
        var suiteId: Int? = null

        /**
         * Configures the base URL for the TestRail API.
         *
         * @param url The base URL of the TestRail server.
         * @return Returns an instance of TCredential for chaining configuration.
         */
        fun url(url: String): TUsername {
            baseUrl = url
            return TestRail()
        }
    }

    // These fields are used internally for authentication purposes
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var apiToken: String
    private var projectId: Int? = null
    private var suiteId: Int? = null

    override fun username(username: String): TPassword {
        this.username = username

        return this
    }

    override fun password(password: String): TestRailFunctions {
        this.password = password
        token = Base64.getEncoder().encodeToString("$username:$password".toByteArray())

        return this
    }
    override fun apiToken(apiToken: String): TestRailFunctions {
        this.apiToken = apiToken
        token = Base64.getEncoder().encodeToString("$username:$apiToken".toByteArray())

        return this
    }

    override fun projectId(id: Int): TestRailFunctionsForConfiguredProject {
        this.projectId = id

        return this
    }

    override fun suiteId(id: Int): TestRailFunctionsForConfiguredProjectAndSuite {
        this.suiteId = id

        return this
    }

    // Functions without Project ID and Suite ID

    override fun getProjects(): Set<ProjectDTO> =
        Projects.get()

    override fun getCase(caseId: Int): CaseDTO =
        Cases.getCase(caseId)

    override fun getRun(runId: Int): RunDTO =
        Run.get(runId)

    override fun getTests(runId: Int): List<TestDTO> =
        Tests.getAll(runId)

    override fun getCaseIds(runId: Int): List<Int> =
        Tests.getCaseIds(runId)

    override fun getSuites(projectId: Int): Set<SuiteDTO> =
        Suites.get(projectId)

    override fun getFirstFoundSuiteIdByName(name: String, projectId: Int): Int? =
        getSuites(projectId).firstOrNull { it.name.lowercase() == name.lowercase() }?.id

    override fun getFirstFoundProjectIdByName(name: String): Int? =
        getProjects().firstOrNull { it.name.lowercase() == name.lowercase() }?.id

    override fun getCases(projectId: Int, suiteId: Int): Set<CaseDTO> =
        Cases.getAll(projectId, suiteId)

    override fun createRun(name: String): ProjectId =
        TestRailRunBuilder.name(name)

    override fun getSections(projectId: Int, suiteId: Int): Set<SectionDTO> =
        Sections.getAll(projectId, suiteId)

    override fun getSectionsWithChildren(projectId: Int, suiteId: Int, sectionsId: List<Int>): Set<SectionDTO> =
        Sections.getSectionsWithChildren(projectId, suiteId, sectionsId)

    override fun getSectionWithChildren(projectId: Int, suiteId: Int, sectionId: Int): Set<SectionDTO> =
        getSectionsWithChildren(projectId, suiteId, listOf(sectionId))

    override fun getMainSectionForChild(projectId: Int, suiteId: Int, sectionId: Int): SectionDTO? =
        Sections.getMainSectionForChild(projectId, suiteId, sectionId)

    // Functions with configured Project ID

    override fun getSuites(): Set<SuiteDTO> =
        getSuites(projectId!!)

    override fun getCases(suiteId: Int): Set<CaseDTO> =
        getCases(projectId!!, suiteId)

    override fun createRunForCurrentProject(name: String): SuiteId =
        TestRailRunBuilder.name(name).projectId(projectId!!)

    override fun getSections(suiteId: Int): Set<SectionDTO> =
        getSections(projectId!!, suiteId)

    override fun getSectionsWithChildren(suiteId: Int, sectionsId: List<Int>): Set<SectionDTO> =
        getSectionsWithChildren(projectId!!, suiteId, sectionsId)

    override fun getSectionWithChildren(suiteId: Int, sectionId: Int): Set<SectionDTO> =
        getSectionsWithChildren(suiteId, listOf(sectionId))

    override fun getMainSectionForChild(suiteId: Int, sectionId: Int): SectionDTO? =
        getMainSectionForChild(projectId!!, suiteId, sectionId)

    override fun getFirstFoundSuiteIdByName(name: String): Int? =
        getFirstFoundSuiteIdByName(name, projectId!!)

    // Function with configured Project ID and Suite ID

    override fun getCases(): Set<CaseDTO> =
        getCases(suiteId!!)

    override fun createRunForCurrentProjectAndSuite(name: String): TestRunFunctions =
        TestRailRunBuilder.name(name).projectId(projectId!!).suiteId(suiteId!!)

    override fun getSections(): Set<SectionDTO> =
        getSections(suiteId!!)

    override fun getSectionsWithChildren(sectionsId: List<Int>): Set<SectionDTO> =
        getSectionsWithChildren(suiteId!!, sectionsId)

    override fun getSectionWithChildren(sectionId: Int): Set<SectionDTO> =
        getSectionsWithChildren(listOf(sectionId))

    override fun getMainSectionForChild(sectionId: Int): SectionDTO? =
        getMainSectionForChild(projectId!!, suiteId!!, sectionId)

    override fun updateTestCases(id: List<Int>, fields: Map<String, Any?>): Set<CaseDTO> {
        return updateTestCases(id, suiteId!!, fields)
    }

    // Common functions

    override fun updateTestCase(id: Int, fields: Map<String, Any?>): CaseDTO {
        return Cases.update(id, fields)
    }

    override fun updateTestCases(id: List<Int>, suiteId: Int, fields: Map<String, Any?>): Set<CaseDTO> {
        return Cases.update(id, suiteId, fields)
    }

    override fun updateTestCase(case: CaseDTO): CaseDTO {
        return Cases.update(case)
    }
}