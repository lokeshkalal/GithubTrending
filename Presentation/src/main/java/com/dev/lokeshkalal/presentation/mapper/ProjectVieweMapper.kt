package com.dev.lokeshkalal.presentation.mapper

import com.dev.lokeshkalal.domain.model.Project
import com.dev.lokeshkalal.presentation.model.ProjectView
import javax.inject.Inject

open class ProjectVieweMapper @Inject constructor() : Mapper<ProjectView, Project> {
    override fun mapToView(type: Project): ProjectView {
        return ProjectView(
            type.id, type.name, type.fullName,
            type.startCount, type.dateCreated, type.ownerName,
            type.ownerAvatar, type.isBookMarked
        )
    }
}