package com.github.djuise.testrail.api.requests

import com.github.djuise.testrail.api.TestRailRequest
import com.github.djuise.testrail.api.dto.SectionDTO
import com.github.djuise.testrail.api.dto.SectionsDTO

object Sections {

    fun getAll(projectId: Int, suiteId: Int): List<SectionDTO> {

        fun getSections(projectId: Int, suiteId: Int, offset: Int, limit: Int): SectionsDTO {
            return TestRailRequest.get("get_sections/$projectId&suite_id=$suiteId&limit=$limit&offset=$offset", SectionsDTO::class.java)
        }

        val allSections = mutableListOf<SectionDTO>()
        var offset = 0
        val limit = 250
        var hasMoreCases = true

        while (hasMoreCases) {
            val sections = getSections(projectId, suiteId, offset, limit)
            allSections.addAll(sections.sections)

            if (sections.sections.size < limit) {
                hasMoreCases = false
            } else {
                offset += limit
            }
        }

        return allSections
    }

    fun getChildrenForSections(projectId: Int, suiteId: Int, parentIds: List<Int>): List<Int> {
        val allSections = getAll(projectId, suiteId)
        val result = mutableSetOf<Int>()
        val stack = ArrayDeque(parentIds) // Стек для обработки секций

        while (stack.isNotEmpty()) {
            val currentId = stack.removeLast() // Берем следующий ID из стека
            if (result.add(currentId)) { // Если секция еще не обработана
                val children = allSections.filter { it.parentId == currentId }.map { it.id }
                stack.addAll(children) // Добавляем дочерние секции в стек
            }
        }

        return result.toList()
    }
}