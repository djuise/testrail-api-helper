package com.github.djuise.testrail

import org.testng.Assert.assertEquals
import org.testng.Assert.assertTrue
import org.testng.annotations.Test

class MainTest: BaseTest() {

    private val testCaseId = 146586

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

        softAssertTrue(allCases == allCasesWithoutSuite, "All cases != allCasesWithoutSuite")
        softAssertTrue(allCases == allCasesWithoutProject, "All cases != allCasesWithoutProject")
    }

    @Test
    fun updateCase() {
        val testCase = testRail.getCase(testCaseId)
        val currentPriority = testCase.priorityId
        val numbers = (1..5).toList()
        val random = numbers.filter { it != currentPriority }.random()

        testCase.priorityId = random
        testCase.let { testRail.updateTestCase(it) }
        val updatedTestCase = testRail.getCase(testCaseId)

        assertEquals(updatedTestCase.priorityId, random, "Priority not as expected")
    }

    @Test
    fun run() {
        val testCase = testRail.getCase(testCaseId)
        val run = testRail.createRunForCurrentProjectAndSuite("Test")
            .description("Test")
            .cases(setOf(testCase))
            .create()

        val testRun = testRail.getRun(run.id)

        softAssertEquals(testRun.name, run.name, "Created run name not equals to fetched test run name")
        softAssertEquals(testRun.description, run.description, "Created run description not equals to fetched test run description")
        softAssertEquals(testRun.projectId, run.projectId, "Created run projectId not equals to fetched test run projectId")
        softAssertEquals(testRun.suiteId, run.suiteId, "Created run suiteId not equals to fetched test run suiteId")
        softAssertTrue(!testRun.includeAll, "Created run includeAll not equals to fetched test run includeAll")
        softAssertEquals(testRun.completedOn, null, "completedOn is not null")

        try {
            val allTests = testRail.getTests(run.id)
            softAssertEquals(allTests.size, 1)
            softAssertEquals(allTests.first().caseId, testCaseId)

            val newName = "Test name 2"
            val newDes = "Test description 2"
            testRun.name = newName
            testRun.description = newDes

            val updatedRun = testRail.updateRun(testRun)

            softAssertEquals(updatedRun.name, newName, "Updated name not equals to expected")
            softAssertEquals(updatedRun.description, newDes, "Updated description not equals to expected")

        } catch (e: Throwable) {
            softAssertFail(e)
        } finally {
            testRail.deleteRun(run.id)
        }
    }

    @Test
    fun createEmptyRun() {
        val run = testRail.createRunForCurrentProjectAndSuite("Test")
            .description("Test")
            .cases(emptySet())
            .create()

        softAssertFalse(run.includeAll, "Run has includeAll == true")

        try {
            val allTests = testRail.getTests(run.id)
            softAssertEquals(allTests.size, 0)
        } finally {
            val closedRun = testRail.closeRun(run.id)
            softAssertFalse(closedRun.includeAll, "Closed run has includeAll == true")
            softAssertTrue(closedRun.completedOn != null, "CompletedOn == null")
            assertTrue(closedRun.isCompleted, "Closed run not is completed")
        }
    }

    @Test
    fun createFullRun() {
        val run = testRail.createRunForCurrentProjectAndSuite("Test")
            .description("Test")
            .create()

        softAssertTrue(run.includeAll, "Run's includeAll == false")

        try {
            val allTests = testRail.getTests(run.id)
            softAssertTrue(allTests.isNotEmpty(), "All tests is empty")
        } finally {
            testRail.deleteRun(run.id)
        }
    }

    @Test
    fun getProjects() {
        val projects = testRail.getProjects()
        assert(projects.size > 1)
    }

    @Test
    fun getStatuses() {
        val statuses = testRail.getStatuses()
        assert(statuses.size > 1)
    }
}