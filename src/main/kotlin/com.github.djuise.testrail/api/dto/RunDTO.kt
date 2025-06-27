package com.github.djuise.testrail.api.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.github.djuise.testrail.api.helpers.UnixTimestampDeserializer
import java.util.*

data class CreateRunDTO(
    val name: String,
    val suiteId: Int?,
    val description: String?,
    val caseIds: Set<Int>
) {
    val includeAll: Boolean = caseIds.isEmpty()
}

data class RunDTO(
    val id: Int,
    val projectId: Int,
    val suiteId: Int,
    val name: String,
    val description: String?,
    val milestoneId: Int?,
    val assignedtoId: Int?,
    val includeAll: Boolean,
    val isCompleted: Boolean,
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val completedOn: Date?,
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val createdOn: Date?,
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val updatedOn: Date?
)