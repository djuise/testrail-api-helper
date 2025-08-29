package com.github.djuise.testrail.api.dto

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.github.djuise.testrail.api.helpers.UnixTimestampDeserializer
import java.util.*

data class CasesDTO(val cases: Set<CaseDTO>)
data class UpdatedCasesDTO(@JsonProperty("updated_cases") val updatedCases: Set<CaseDTO>)

data class CaseDTO(
    val id: Int,
    var title: String,
    var estimate: String?,
    var refs: String?,
    @JsonProperty("created_by")
    var createdBy: Int,
    @JsonProperty("created_on")
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    var createdOn: Date,
    @JsonProperty("estimate_forecast")
    var estimateForecast: String?,
    @JsonProperty("milestone_id")
    var milestoneId: Int?,
    @JsonProperty("priority_id")
    var priorityId: Int?,
    @JsonProperty("section_id")
    var sectionId: Int,
    @JsonProperty("suite_id")
    var suiteId: Int,
    @JsonProperty("template_id")
    var templateId: Int?,
    @JsonProperty("type_id")
    var typeId: Int?,
    @JsonProperty("updated_by")
    val updatedBy: Int?,
    @JsonProperty("updated_on")
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val updatedOn: Date?,
    @JsonProperty("is_deleted")
    val isDeleted: Int?,
    @JsonProperty("display_order")
    val displayOrder: Int?,
    @JsonIgnore
    val otherFields: MutableMap<String, Any?> = mutableMapOf()
) {
    // Needs to set other fields into otherFields
    @JsonAnySetter
    fun setOtherFields(key: String, value: Any?) {
        otherFields[key] = value
    }

    // Needs to get other fields as their fields for JSON
    @JsonAnyGetter
    fun getDynamicFields(): Map<String, Any?> = otherFields
}