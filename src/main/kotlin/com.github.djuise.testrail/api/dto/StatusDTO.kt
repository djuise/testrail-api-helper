package com.github.djuise.testrail.api.dto

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

data class StatusDTO(
    val id: Int,
    val name: String,
    val label: String,
    @JsonProperty("is_final")
    val isFinal: Boolean,
    @JsonProperty("is_system")
    val isSystem: Boolean,
    @JsonProperty("is_untested")
    val isUntested: Boolean,
    @JsonProperty("color_bright")
    val colorBright: Int,
    @JsonProperty("color_dark")
    val colorDark: Int,
    @JsonProperty("color_medium")
    val colorMedium: Int,
    @JsonIgnore
    val otherFields: MutableMap<String, Any?> = mutableMapOf()
) {
    // Needs to get other fields as their fields for JSON
    @JsonAnyGetter
    fun getDynamicFields(): Map<String, Any?> = otherFields
}
