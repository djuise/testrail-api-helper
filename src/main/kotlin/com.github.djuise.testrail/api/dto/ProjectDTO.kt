package com.github.djuise.testrail.api.dto

data class ProjectsDTO(val projects: Set<ProjectDTO>)
data class ProjectDTO(val id: Int, val name: String)