package com.github.djuise.testrail.api.helpers

import com.github.djuise.testrail.api.dto.CaseDTO
import com.github.djuise.testrail.api.dto.CreateRunDTO
import com.github.djuise.testrail.api.dto.RunDTO
import com.github.djuise.testrail.api.requests.Run

/**
 * The `TestRailRunBuilder` class is a builder class to create and manage TestRail test runs.
 * It allows setting various parameters for a test run such as suite ID, description, and test cases, and supports creating the run in TestRail.
 *
 * @property name The name of the test run.
 */
class TestRailRunBuilder private constructor(private val name: String): ProjectId, SuiteId, TestRunFunctions {

    private var projectId: Int = -1
    private var suiteId: Int? = null
    private var description: String? = null
    private var casesId: MutableSet<Int>? = null

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

    override fun projectId(id: Int): TestRailRunBuilder {
        this.projectId = id

        return this
    }

    override fun suiteId(id: Int): TestRailRunBuilder {
        this.suiteId = id

        return this
    }

    override fun description(description: String): TestRunFunctions {
        this.description = description

        return this
    }

    override fun casesById(casesId: Set<Int>): TestRunFunctions {
        if (this.casesId == null)
            this.casesId = mutableSetOf()

        this.casesId!!.addAll(casesId)

        return this
    }

    override fun cases(casesList: Set<CaseDTO>): TestRunFunctions {
        this.casesById(casesList.map { it.id }.toSet())

        return this
    }

    override fun create(): RunDTO {
        val run = CreateRunDTO(name, description, suiteId, casesId)

        return Run.create(projectId, run)
    }
}