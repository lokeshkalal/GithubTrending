package com.dev.lokeshkalal.ui.mapper

import com.dev.lokeshkalal.presentation.model.ProjectView
import com.dev.lokeshkalal.ui.model.Project
import javax.inject.Inject

class ProjectViewMapper @Inject constructor() : ViewMapper<ProjectView, Project> {
    override fun mapToView(presentation: ProjectView): Project {
        return Project(
            presentation.id, presentation.name,
            presentation.fullName, presentation.starCount,
            presentation.dateCreated, presentation.ownerName,
            presentation.ownerAvatar, presentation.isBookmarked
        )

    }
}