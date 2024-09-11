package com.github.djuise.testrail.api.requests

import com.github.djuise.testrail.api.TestRailRequest
import com.github.djuise.testrail.api.dto.RunDTO
import com.github.djuise.testrail.api.helpers.TestRailException
import com.github.djuise.testrail.api.helpers.objectMapper

object Run {

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