package com.github.djuise.testrail.api.dto

data class SectionsDTO(val sections: List<SectionDTO>)

data class SectionDTO(
    val id: Int,
    val parentId: Int?,
)