package com.github.djuise.testrail.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.github.djuise.testrail.api.helpers.UnixTimestampDeserializer
import java.util.*

data class CreateRunDTO(
    val name: String,
    val description: String?,
    @JsonProperty("suite_id")
    val suiteId: Int?,
    @JsonProperty("case_ids")
    val caseIds: Set<Int>? = null
) {
    @JsonProperty("include_all")
    val includeAll: Boolean = caseIds == null
}

data class RunDTO(
    val id: Int,
    var name: String,
    var description: String?,
    @JsonProperty("project_id")
    val projectId: Int,
    @JsonProperty("suite_id")
    val suiteId: Int,
    @JsonProperty("milestone_id")
    var milestoneId: Int?,
    @JsonProperty("assignedto_id")
    var assignedtoId: Int?,
    @JsonProperty("include_all")
    var includeAll: Boolean,
    @JsonProperty("is_completed")
    var isCompleted: Boolean,
    @JsonProperty("completed_on")
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val completedOn: Date?,
    @JsonProperty("created_on")
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val createdOn: Date?,
    @JsonProperty("updated_on")
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val updatedOn: Date?
)