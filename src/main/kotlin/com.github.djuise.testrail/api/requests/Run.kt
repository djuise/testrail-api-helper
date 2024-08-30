package com.github.djuise.testrail.api.requests

import com.github.djuise.testrail.api.TestRailRequest
import com.github.djuise.testrail.api.dto.RunDTO
import com.github.djuise.testrail.api.helpers.TestRailException
import com.github.djuise.testrail.api.helpers.objectMapper

object Run {
//class Run private constructor(private val name: String): ProjectId {
//
//    private var projectId: Int = -1
//    private var suiteId: Int? = null
//    private var description: String? = null
//    private var casesId: List<Int>? = null
//
//    companion object {
//        fun name(name: String): ProjectId {
//            return Run(name)
//        }
//    }
//
//    override fun projectId(id: Int): Run {
//        this.projectId = id
//
//        return this
//    }
//
//    fun suiteId(id: Int): Run {
//        this.suiteId = id
//
//        return this
//    }
//
//    fun description(description: String): Run {
//        this.description = description
//
//        return this
//    }
//
//    @JvmName("casesById")
//    fun cases(casesId: List<Int>): Run {
//        this.casesId = casesId
//
//        return this
//    }
//
//    @JvmName("casesByList")
//    fun cases(casesList: List<CaseDTO>): Run {
//        this.casesId = casesList.map { it.id }
//
//        return this
//    }
//
//    fun cases(cases: CasesDTO): Run {
//        this.casesId = cases.cases.map { it.id }
//
//        return this
//    }

    fun create(projectId: Int, run: RunDTO): Int {
        val runJson = objectMapper.writeValueAsString(run)

        val response = TestRailRequest.post("index.php?/api/v2/add_run/$projectId", runJson)

        if (response.isSuccessful) {
            val rootNode = objectMapper.readTree(response.body?.string())
            return rootNode.get("id").asInt()
        } else {
            throw TestRailException("""Failed to create Test Run.
                |message: ${response.message}
                |body: ${response.body?.string()}
            """.trimMargin())
        }
    }
}