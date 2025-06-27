package com.github.djuise.testrail.api.helpers

import com.github.djuise.testrail.api.dto.CaseDTO

interface ProjectId {
    /**
     * Sets the project ID for the test run.
     *
     * @param id The project ID.
     * @return Returns this `Run` instance to allow method chaining.
     */
    fun projectId(id: Int): SuiteId
}

interface SuiteId: TestRunFunctions {
    /**
     * Sets the suite ID for the test run.
     * Optional if the project is operating in single suite mode, required otherwise.
     *
     * @param id The suite ID.
     * @return Returns this `Run` instance to allow method chaining.
     */
    fun suiteId(id: Int): TestRunFunctions
}

interface TestRunFunctions {
    /**
     * Sets the description for the test run.
     * Optional function
     *
     * @param description The description of the test run.
     * @return Returns this `Run` instance to allow method chaining.
     */
    fun description(description: String): TestRunFunctions

    /**
     * Sets the case IDs directly for the test run.
     * Optional function. If it will be skipped then all testcases from the suite will be added.
     *
     * @param casesId The list of case IDs to include in the test run.
     * @return Returns this `Run` instance to allow method chaining.
     */
    fun casesById(casesId: Set<Int>): TestRunFunctions

    /**
     * Sets the case IDs for the test run based on a list of `CaseDTO`.
     * Optional function. If it will be skipped then all testcases from the suite will be added.
     *
     * @param casesList The list of `CaseDTO` from which to extract the IDs.
     * @return Returns this `Run` instance to allow method chaining.
     */
    fun cases(casesList: Set<CaseDTO>): TestRunFunctions

    /**
     * Creates the test run in TestRail and returns the new run ID.
     *
     * @return The ID of the newly created test run.
     * @throws TestRailException if the request to create the test run fails.
     */
    fun create(): Int
}