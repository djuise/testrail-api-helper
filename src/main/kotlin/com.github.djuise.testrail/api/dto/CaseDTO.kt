package com.github.djuise.testrail.api.dto

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.github.djuise.testrail.api.helpers.UnixTimestampDeserializer
import java.util.*

data class CasesDTO(val cases: List<CaseDTO>)
data class UpdatedCasesDTO(val updatedCases: List<CaseDTO>)

data class CaseDTO(
    val id: Int,
    var title: String,
    var createdBy: Int,
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    var createdOn: Date,
    var estimate: String?,
    var estimateForecast: String?,
    var milestoneId: Int?,
    var priorityId: Int?,
    var refs: String?,
    var sectionId: Int?,
    var suiteId: Int,
    var templateId: Int?,
    var typeId: Int?,
    val updatedBy: Int?,
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val updatedOn: Date?,
    val isDeleted: Int?,
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
    fun getDynamicFields(): Map<String, Any?> =
        otherFields.mapKeys { (key, _) -> key.toSnakeCase() }

    private fun String.toSnakeCase() =
        replace(Regex("([a-z])([A-Z])"), "$1_$2").lowercase()
}