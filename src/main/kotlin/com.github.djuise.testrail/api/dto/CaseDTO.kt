package com.github.djuise.testrail.api.dto

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.github.djuise.testrail.api.helpers.UnixTimestampDeserializer
import java.util.*

data class CasesDTO(var cases: List<CaseDTO>)

data class CaseDTO(
    val id: Int,
    val title: String,
    val createdBy: Int,
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val createdOn: Date,
    val estimate: String?,
    val estimateForecast: String?,
    val milestoneId: Int?,
    val priorityId: Int?,
    val refs: String?,
    val sectionId: Int?,
    val suiteId: Int,
    val templateId: Int?,
    val typeId: Int?,
    val updatedBy: Int?,
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val updatedOn: Date?,
    val isDeleted: Int?,
    val displayOrder: Int?,
    val otherFields: MutableMap<String, Any?> = mutableMapOf()
) {
    @JsonAnySetter
    fun setDynamicField(key: String, value: Any?) {
        otherFields[key] = value
    }
}