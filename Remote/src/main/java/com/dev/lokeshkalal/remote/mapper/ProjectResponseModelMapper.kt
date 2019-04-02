package com.dev.lokeshkalal.remote.mapper

import com.dev.lokeshkalal.data.model.ProjectEntity
import com.dev.lokeshkalal.remote.model.ProjectModel
import javax.inject.Inject

open class ProjectResponseModelMapper @Inject constructor() : ModelMapper<ProjectModel, ProjectEntity> {
    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(
            model.id,
            model.name,
            model.fullName,
            model.starCount.toString(),
            model.dateCreated,
            model.owner.ownerName,
            model.owner.ownerAvatar,
            false
        )
    }
}