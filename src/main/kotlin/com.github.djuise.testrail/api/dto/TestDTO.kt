package com.github.djuise.testrail.api.dto

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.github.djuise.testrail.api.helpers.UnixTimestampDeserializer
import java.util.*

data class TestsDTO(val tests: List<TestDTO>)

data class TestDTO(
    val id: Int,
    @JsonProperty("case_id")
    val caseId: Int,
    val status: Int?,
    @JsonProperty("assignedto_id")
    val assignedtoId: Int?,
    @JsonProperty("run_id")
    val runId: Int,
    val title: String?,
    @JsonProperty("template_id")
    val templateId: Int?,
    @JsonProperty("type_id")
    val typeId: Int?,
    val priority: Int?,
    @JsonProperty("estimate")
    val estimate: String?,
    @JsonProperty("estimate_forecast")
    val estimateForecast: String?,
    val refs: String?,
    @JsonProperty("milestone_id")
    val milestoneId: Int?,
    @JsonProperty("created_on")
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val createdOn: Date?,
    @JsonProperty("updated_on")
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val updatedOn: Date?,
    @JsonProperty("created_by")
    val createdBy: Int?,
    @JsonProperty("updated_by")
    val updatedBy: Int?,
    @JsonProperty("is_deleted")
    val isDeleted: Int?,
    @JsonProperty("display_order")
    val displayOrder: Int?,
    @JsonProperty("section_id")
    val sectionId: Int?,
    @JsonProperty("suite_id")
    val suiteId: Int?,
    var labels: List<String>?,
    @JsonProperty("status_id")
    val statusId: Int,
    @JsonProperty("priority_id")
    val priorityId: Int,
    @JsonIgnore
    val otherFields: MutableMap<String, Any?> = mutableMapOf()
) {
    // Needs to get other fields as their fields for JSON
    @JsonAnyGetter
    fun getDynamicFields(): Map<String, Any?> = otherFields
}
