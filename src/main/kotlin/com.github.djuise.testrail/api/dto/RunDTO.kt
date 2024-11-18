package com.github.djuise.testrail.api.dto

data class RunDTO(
    val name: String,
    val suiteId: Int?,
    val description: String?,
    val caseIds: List<Int>?
) {
    val includeAll: Boolean = caseIds.isNullOrEmpty()
}