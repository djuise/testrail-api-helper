package com.github.djuise.testrail.api.dto

data class SectionsDTO(val sections: List<SectionDTO>)

data class SectionDTO(
    val id: Int,
    val parentId: Int?,
    val depth: Int,
    val description: String?,
    val name: String,
    val displayOrder: Int,
    val suiteId: Int
)