package com.github.djuise.testrail.api.dto

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.module.kotlin.convertValue
import com.github.djuise.testrail.api.helpers.UnixTimestampDeserializer
import com.github.djuise.testrail.api.helpers.jsonMapper
import com.github.djuise.testrail.api.helpers.objectMapper
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
    var otherFields: MutableMap<String, Any?> = mutableMapOf()
) {
    @JsonAnySetter
    fun setDynamicField(key: String, value: Any?) {
        otherFields[key] = value
    }

    fun toStringJson(): String {
        val testCaseMap = objectMapper.convertValue<Map<String, Any?>>(this)

        val resultMap = testCaseMap.toMutableMap()
        val otherFields = resultMap.remove("otherFields") as? Map<String, Any?>
        if (otherFields != null) {
            resultMap.putAll(otherFields)
        }

        return jsonMapper.writeValueAsString(resultMap)
    }
}