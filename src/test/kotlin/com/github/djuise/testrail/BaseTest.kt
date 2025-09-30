package com.github.djuise.testrail

import com.github.djuise.testrail.helpers.FileProperties
import org.testng.annotations.AfterTest
import org.testng.asserts.SoftAssert

open class BaseTest {

    val url = FileProperties.testRailUrl!!
    val username = FileProperties.username!!
    val apiToken = FileProperties.apiToken!!
    val projectId = FileProperties.projectId!!
    val suiteId = FileProperties.suiteId!!

    val testRail = TestRail.url(url)
        .username(username)
        .apiToken(apiToken)
        .projectId(projectId)
        .suiteId(suiteId)

    private val softAssert = SoftAssert()

    @AfterTest
    fun tearDown() {
        softAssert.assertAll()
    }

    fun softAssertTrue(condition: Boolean, failureMessage: String? = null) {
        softAssert.assertTrue(condition, failureMessage)
    }

    fun softAssertFalse(condition: Boolean, failureMessage: String? = null) {
        softAssert.assertFalse(condition, failureMessage)
    }

    fun <T>softAssertEquals(actual: T, expected: T, failureMessage: String? = null) {
        softAssert.assertEquals(actual, expected, failureMessage)
    }

    fun softAssertFail(e: Throwable) {
        softAssert.fail(e.message)
    }
}