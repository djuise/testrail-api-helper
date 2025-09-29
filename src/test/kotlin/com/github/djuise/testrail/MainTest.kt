package com.github.djuise.testrail

import com.github.djuise.testrail.helpers.FileProperties
import org.junit.jupiter.api.Test

class MainTest {

    private val url = FileProperties.testRailUrl!!
    private val username = FileProperties.username!!
    private val apiToken = FileProperties.apiToken!!
    private val projectId = FileProperties.projectId!!
    private val suiteId = FileProperties.suiteId!!
    private val testCaseId = 146586

    private val testRail = TestRail.url(url)
        .username(username)
        .apiToken(apiToken)
        .projectId(projectId)
        .suiteId(suiteId)

    @Test
    fun getCases() {
        val allCases = testRail
            .getCases()

        val allCasesWithoutSuite = TestRail.url(url)
            .username(username)
            .apiToken(apiToken)
            .projectId(projectId)
            .getCases(suiteId)

        val allCasesWithoutProject = TestRail.url(url)
            .username(username)
            .apiToken(apiToken)
            .getCases(projectId, suiteId)

        assert( allCases == allCasesWithoutSuite && allCases == allCasesWithoutProject )
    }

    @Test
    fun updateCase() {
        val testCase = testRail.getCase(testCaseId)
        val currentPriority = testCase.priorityId
        val numbers = (1..5).toList()
        val random = numbers.filter { it != currentPriority }.random()

        println("New priority = $random")

        testCase.priorityId = random
        testCase.let { testRail.updateTestCase(it) }
        val updatedTestCase = testRail.getCase(testCaseId)
        assert(updatedTestCase.priorityId == random)
    }

    @Test
    fun createRun() {
        val testCase = testRail.getCase(testCaseId)
        val run = testRail.createRunForCurrentProjectAndSuite("Test")
            .description("Test")
            .cases(setOf(testCase))
            .create()
        println(run)

        val testRun = testRail.getRun(run.id)

        assert(testRun.name == run.name)
        assert(testRun.description == run.description)
        assert(testRun.projectId == run.projectId)
        assert(testRun.suiteId == run.suiteId)
        assert(!testRun.includeAll)
        assert(testRun.completedOn == null)

        val allTests = testRail.getTests(run.id)

        assert(allTests.size == 1)
        assert(allTests.first().caseId == testCaseId)
    }

    @Test
    fun getProjects() {
        val projects = testRail.getProjects()
        assert(projects.size > 1)
    }
}