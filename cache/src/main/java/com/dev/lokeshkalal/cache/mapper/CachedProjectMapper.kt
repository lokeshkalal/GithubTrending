package com.dev.lokeshkalal.cache.mapper

import com.dev.lokeshkalal.cache.model.CachedProject
import com.dev.lokeshkalal.data.model.ProjectEntity
import javax.inject.Inject

class CachedProjectMapper @Inject constructor(): CacheMapper<CachedProject, ProjectEntity> {
    override fun mapFromcached(type: CachedProject): ProjectEntity {
        return ProjectEntity(
            type.id, type.name, type.fullName, type.starCount,
            type.dateCreated, type.ownerName, type.ownerAvatar,
            type.isBookmarked
        )
    }

    override fun mapToCached(type: ProjectEntity): CachedProject {
        return CachedProject(
            type.id, type.name, type.fullName, type.startCount,
            type.dateCreated, type.ownerName, type.ownerAvatar,
            type.isBookMarked
        )
    }
}