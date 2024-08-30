package com.github.djuise.testrail

import com.github.djuise.testrail.api.dto.CasesDTO
import com.github.djuise.testrail.api.dto.ProjectsDTO
import com.github.djuise.testrail.api.dto.Suite
import com.github.djuise.testrail.api.helpers.ProjectId
import com.github.djuise.testrail.api.helpers.TestRailRunBuilder
import com.github.djuise.testrail.api.requests.Cases
import com.github.djuise.testrail.api.requests.Projects
import com.github.djuise.testrail.api.requests.Suites
import com.github.djuise.testrail.helpers.TCredential
import com.github.djuise.testrail.helpers.TPassword
import com.github.djuise.testrail.helpers.TestRailFunctions
import java.util.*

/**
 * The `TestRail` class provides an interface to interact with the TestRail API.
 * It supports authentication via username and password or API token, and offers methods to manage projects, test suites, and test cases.
 */

object TestRail: TCredential, TPassword, TestRailFunctions {

    /** The base URL of the TestRail server. */
    lateinit var url: String

    /** The authentication token used for API requests. Generated automatically depends on provided credentials. */
    lateinit var token: String

    // These fields are used internally for authentication purposes
    private lateinit var username: String // ?
    private lateinit var password: String // ?
    private lateinit var apiToken: String // ?

    /**
     * Configures the base URL for the TestRail API.
     *
     * @param url The base URL of the TestRail server.
     * @return Returns an instance of TCredential for chaining configuration.
     */
    fun url(url: String): TCredential {
        this.url = url

        return this
    }

    /**
     * Sets the username for API authentication.
     *
     * @param username The username used for authentication.
     * @return Returns an instance of TPassword to continue the authentication process.
     */
    override fun username(username: String): TPassword {
        this.username = username

        return this
    }

    /**
     * Sets the password for API authentication and encodes credentials.
     *
     * @param password The password used for authentication.
     * @return Returns an instance of TestRailFunctions allowing access to API functions.
     */
    override fun password(password: String): TestRailFunctions {
        this.password = password
        this.token = Base64.getEncoder().encodeToString("$username:$password".toByteArray())

        return this
    }

    /**
     * Sets the API token for authentication and encodes it.
     *
     * @param token The API token provided by TestRail.
     * @return Returns an instance of TestRailFunctions allowing access to API functions.
     */
    override fun apiToken(token: String): TestRailFunctions {
        this.apiToken = token
        this.token = Base64.getEncoder().encodeToString("$username:$apiToken".toByteArray())

        return this
    }

    /**
     * Retrieves a list of all projects from TestRail.
     *
     * @return Returns a ProjectsDTO containing a list of projects.
     */
    override fun getProjects(): ProjectsDTO {
        return Projects.get()
    }

    /**
     * Searches for the first project by name and returns its ID.
     *
     * @param name The name of the project to search for.
     * @return Returns the ID of the first found project or null if no match is found.
     */
    override fun getFirstFoundProjectIdByName(name: String): Int? {
        return getProjects().projects.firstOrNull { it.name.lowercase() == name.lowercase() }?.id
    }

    /**
     * Retrieves a list of test suites associated with a given project ID.
     *
     * @param projectId The ID of the project.
     * @return Returns a list of Suite objects.
     */
    override fun getSuites(projectId: Int): List<Suite> {
        return Suites.get(projectId)
    }

    /**
     * Searches for the first suite by name within a specific project and returns its ID.
     *
     * @param name The name of the suite to search for.
     * @param projectId The ID of the project that the suite belongs to.
     * @return Returns the ID of the first found suite or null if no match is found.
     */
    override fun getFirstFoundSuiteIdByName(name: String, projectId: Int): Int? {
            return getSuites(projectId).firstOrNull { it.name.lowercase() == name.lowercase() }?.id
    }

    /**
     * Retrieves all cases for a given project and suite ID.
     *
     * @param projectId The project ID.
     * @param suiteId The suite ID within the project.
     * @return Returns a CasesDTO containing a list of test cases.
     */
    override fun getCases(projectId: Int, suiteId: Int): CasesDTO {
        return Cases.getAll(projectId, suiteId)
    }

    /**
     * Creates a new test run TestRail run builder within TestRail.
     *
     * @param name The name of the new test run.
     * @return Returns a TestRailRunBuilder.
     */
    override fun createRun(name: String): ProjectId {
        return TestRailRunBuilder.name(name)
    }
}