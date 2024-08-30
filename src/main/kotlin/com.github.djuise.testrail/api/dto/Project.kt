package com.github.djuise.testrail.api.dto

data class ProjectsDTO(val projects: List<ProjectDTO>)
data class ProjectDTO(val id: Int, val name: String)