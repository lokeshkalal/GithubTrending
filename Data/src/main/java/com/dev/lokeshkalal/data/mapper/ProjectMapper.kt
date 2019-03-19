package com.dev.lokeshkalal.data.mapper

import com.dev.lokeshkalal.data.model.ProjectEntity
import com.dev.lokeshkalal.domain.model.Project
import javax.inject.Inject

class ProjectMapper @Inject constructor() : EntityMapper<ProjectEntity, Project> {
    override fun mapToEntity(domain: Project): ProjectEntity {
        return ProjectEntity(
            domain.id, domain.name, domain.fullName,
            domain.startCount, domain.dateCreated, domain.ownerName,
            domain.ownerAvatar, domain.isBookMarked
        )
    }

    override fun mapFromEntity(entity: ProjectEntity): Project {
        return Project(
            entity.id, entity.name, entity.fullName,
            entity.startCount, entity.dateCreated, entity.ownerName,
            entity.ownerAvatar, entity.isBookMarked
        )
    }

}