package com.github.djuise.testrail.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SectionsDTO(val sections: List<SectionDTO>)

data class SectionDTO(
    val id: Int,
    val depth: Int,
    val description: String?,
    val name: String,
    @JsonProperty("parent_id")
    val parentId: Int?,
    @JsonProperty("display_order")
    val displayOrder: Int,
    @JsonProperty("suite_id")
    val suiteId: Int
)