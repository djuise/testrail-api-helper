package com.github.djuise.testrail

import com.github.djuise.testrail.helpers.FileProperties
import org.junit.jupiter.api.Test

class MainTest {

    @Test
    fun mainTest() {
        val allCases = TestRail.url(FileProperties.testRailUrl)
            .username(FileProperties.username)
            .apiToken(FileProperties.apiToken)
            .projectId(FileProperties.projectId)
            .suiteId(FileProperties.suiteId)
            .getCases()

        val allCasesWithoutSuite = TestRail.url(FileProperties.testRailUrl)
            .username(FileProperties.username)
            .apiToken(FileProperties.apiToken)
            .projectId(FileProperties.projectId)
            .getCases(FileProperties.suiteId)

        val allCasesWithoutProject = TestRail.url(FileProperties.testRailUrl)
            .username(FileProperties.username)
            .apiToken(FileProperties.apiToken)
            .getCases(FileProperties.projectId, FileProperties.suiteId)

        assert( allCases == allCasesWithoutSuite && allCases == allCasesWithoutProject )
    }
}