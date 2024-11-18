package com.github.djuise.testrail.api.helpers

import com.github.djuise.testrail.api.dto.CaseDTO
import com.github.djuise.testrail.api.dto.RunDTO
import com.github.djuise.testrail.api.requests.Run

/**
 * The `TestRailRunBuilder` class is a builder class to create and manage TestRail test runs.
 * It allows setting various parameters for a test run such as suite ID, description, and test cases, and supports creating the run in TestRail.
 *
 * @property name The name of the test run.
 */
class TestRailRunBuilder private constructor(private val name: String): ProjectId {

    private var projectId: Int = -1
    private var suiteId: Int? = null
    private var description: String? = null
    private var casesId: List<Int>? = null

    companion object {
        /**
         * Factory method to instantiate a `Run` object with a given name.
         *
         * @param name The name of the test run.
         * @return Returns an instance of `Run`.
         */
        fun name(name: String): ProjectId {
            return TestRailRunBuilder(name)
        }
    }

    /**
     * Sets the project ID for the test run.
     *
     * @param id The project ID.
     * @return Returns this `Run` instance to allow method chaining.
     */
    override fun projectId(id: Int): TestRailRunBuilder {
        this.projectId = id

        return this
    }

    /**
     * Sets the suite ID for the test run.
     * Optional if the project is operating in single suite mode, required otherwise.
     *
     * @param id The suite ID.
     * @return Returns this `Run` instance to allow method chaining.
     */
    fun suiteId(id: Int): TestRailRunBuilder {
        this.suiteId = id

        return this
    }

    /**
     * Sets the description for the test run.
     * Optional function
     *
     * @param description The description of the test run.
     * @return Returns this `Run` instance to allow method chaining.
     */
    fun description(description: String): TestRailRunBuilder {
        this.description = description

        return this
    }

    /**
     * Sets the case IDs directly for the test run.
     * Optional function. If it will be skipped then all testcases from the suite will be added.
     *
     * @param casesId The list of case IDs to include in the test run.
     * @return Returns this `Run` instance to allow method chaining.
     */
    @JvmName("casesById")
    fun cases(casesId: List<Int>): TestRailRunBuilder {
        this.casesId = casesId

        return this
    }

    /**
     * Sets the case IDs for the test run based on a list of `CaseDTO`.
     * Optional function. If it will be skipped then all testcases from the suite will be added.
     *
     * @param casesList The list of `CaseDTO` from which to extract the IDs.
     * @return Returns this `Run` instance to allow method chaining.
     */
    fun cases(casesList: List<CaseDTO>): TestRailRunBuilder {
        this.casesId = casesList.map { it.id }

        return this
    }

    /**
     * Creates the test run in TestRail and returns the new run ID.
     *
     * @return The ID of the newly created test run.
     * @throws TestRailException if the request to create the test run fails.
     */
    fun create(): Int {
        val run = RunDTO(name, suiteId, description, casesId)

        return Run.create(projectId, run)
    }
}