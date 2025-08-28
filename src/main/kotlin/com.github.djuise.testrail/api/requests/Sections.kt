package com.github.djuise.testrail.api.requests

import com.github.djuise.testrail.api.TestRailRequest
import com.github.djuise.testrail.api.dto.SectionDTO
import com.github.djuise.testrail.api.dto.SectionsDTO

object Sections {

    fun getAll(projectId: Int, suiteId: Int): Set<SectionDTO> {

        fun getSections(projectId: Int, suiteId: Int, offset: Int, limit: Int): SectionsDTO {
            return TestRailRequest.get("get_sections/$projectId&suite_id=$suiteId&limit=$limit&offset=$offset", SectionsDTO::class.java)
        }

        val allSections = mutableSetOf<SectionDTO>()
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

    fun getSectionsWithChildren(projectId: Int, suiteId: Int, parentIds: List<Int>): Set<SectionDTO> {
        val allSections = getAll(projectId, suiteId)
        val result = mutableSetOf<SectionDTO>()
        val stack = ArrayDeque(allSections.filter { it.id in parentIds })

        while (stack.isNotEmpty()) {
            val currentSection = stack.removeLast()
            if (result.add(currentSection)) {
                val children = allSections.filter { it.parentId == currentSection.id }
                stack.addAll(children)
            }
        }

        return result.toSet()
    }

    fun getMainSectionForChild(projectId: Int, suiteId: Int, childId: Int): SectionDTO? {
        val allSections = getAll(projectId, suiteId)
        var section = allSections.firstOrNull { it.id == childId }
        var counter = allSections.size

        while (counter > 0) {
            if (section == null)
                return null

            if (section.parentId == null)
                return section

            val parentSection = allSections.firstOrNull { it.id == section!!.parentId }

            if (parentSection == null)
                return section

            section = parentSection

            counter--
        }

        return section
    }
}