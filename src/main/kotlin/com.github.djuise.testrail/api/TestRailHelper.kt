//package com.github.djuise.testrail.api
//
//import okhttp3.OkHttpClient
//
//object TestRailHelper {
//
//    // Base URL and credentials for TestRail API
//
//
//    // OkHttp client and Jackson ObjectMapper
//    val client = OkHttpClient()
//    val objectMapper = jacksonObjectMapper()
//
//    // Function to send a GET request to TestRail
//
//
//    // Data classes for mapping JSON responses
//    data class Project(val id: Int, val name: String)
//    data class Test(val id: Int, val title: String, val customAutomationStatus: String, val customSmoke: String)
//    data class Run(val name: String, val case_ids: List<Int>)
//
//    fun main() {
//        // Step 1: Get all projects
//        val projectsResponse = get("index.php?/api/v2/get_projects")
//        val projects: List<Project> = objectMapper.readValue(projectsResponse.body?.string() ?: "")
//
//        // Choose a project (e.g., the first one)
//        val project = projects.firstOrNull() ?: throw Exception("No projects found")
//
//        // Step 2: Get all tests in the project with Automation status = automated and smoke = checked
//        val testsResponse = get("index.php?/api/v2/get_tests/${project.id}")
//        val tests: List<Test> = objectMapper.readValue(testsResponse.body?.string() ?: "")
//
//        // Filter tests by the required custom fields
//        val selectedTests = tests.filter { it.customAutomationStatus == "automated" && it.customSmoke == "checked" }
//
//        // Collect the IDs of the selected tests
//        val testCaseIds = selectedTests.map { it.id }
//
//        // Step 3: Create a new Test Run with the selected test case IDs
//        val run = Run(
//            name = "Automated Smoke Test Run",
//            case_ids = testCaseIds
//        )
//
//        // Convert the Run object to JSON
//        val runJson = objectMapper.writeValueAsString(run)
//
//        // Send the POST request to create the Test Run
//        val createRunResponse = post("index.php?/api/v2/add_run/${project.id}", runJson)
//
//        if (createRunResponse.isSuccessful) {
//            println("Test Run created successfully!")
//        } else {
//            println("Failed to create Test Run: ${createRunResponse.message}")
//        }
//    }
//
//
//}