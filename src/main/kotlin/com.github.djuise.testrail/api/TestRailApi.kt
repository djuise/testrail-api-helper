package com.github.djuise.testrail.api

import com.github.djuise.testrail.api.dto.*
import com.github.djuise.testrail.api.helpers.Constants
import retrofit2.Call
import retrofit2.http.*

interface TestRailApi {

    // Projects
    @GET("${Constants.API_V}/get_projects")
    fun getProjects(): Call<ProjectsDTO>

    // Cases
    @GET
    fun getCase(@Url url: String): Call<CaseDTO>

    @GET
    fun getCases(
        @Url url: String,
        @Query("suite_id") suiteId: Int,
        @Query("limit") limit: Int = 250,
        @Query("offset") offset: Int = 0
    ): Call<CasesDTO>

    @POST
    fun updateCase(
        @Url url: String,
        @Body case: CaseDTO
    ): Call<CaseDTO>

    @POST
    fun updateCaseFields(
        @Url url: String,
        @Body fields: Map<String, Any?>
    ): Call<CaseDTO>

    @POST
    fun updateCases(
        @Url url: String,
        @Body data: Map<String, Any?>
    ): Call<UpdatedCasesDTO>

    // Sections
    @GET
    fun getSections(
        @Url url: String,
        @Query("suite_id") suiteId: Int,
        @Query("limit") limit: Int = 250,
        @Query("offset") offset: Int = 0
    ): Call<SectionsDTO>

    // Suites
    @GET
    fun getSuites(@Url url: String): Call<Set<SuiteDTO>>

    // Runs
    @GET
    fun getRun(@Url url: String): Call<RunDTO>

    @POST
    fun createRun(
        @Url url: String,
        @Body run: CreateRunDTO
    ): Call<RunDTO>

    @POST
    fun updateRun(
        @Url url: String,
        @Body run: RunDTO
    ): Call<RunDTO>

    @POST
    fun closeRun(@Url url: String): Call<RunDTO>

    @POST
    fun deleteRun(@Url url: String): Call<Unit>

    // Tests
    @GET
    fun getTests(
        @Url url: String,
        @Query("limit") limit: Int = 250,
        @Query("offset") offset: Int = 0
    ): Call<TestsDTO>

    // Statuses
    @GET("${Constants.API_V}/get_statuses")
    fun getStatuses(): Call<List<StatusDTO>>
}
