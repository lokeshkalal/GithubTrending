package com.dev.lokeshkalal.remote.factory

import com.dev.lokeshkalal.data.model.ProjectEntity
import com.dev.lokeshkalal.remote.model.OwnerModel
import com.dev.lokeshkalal.remote.model.ProjectModel
import com.dev.lokeshkalal.remote.model.ProjectsResponseModel

object ProjectDataFactory {

    fun makeOwner(): OwnerModel {
        return OwnerModel(DataFactory.randomUuid(), DataFactory.randomUuid())
    }

    fun makeProject(): ProjectModel {
        return ProjectModel(
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomInt(),
            DataFactory.randomUuid(), makeOwner()
        )
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), false
        )
    }

    fun makeProjectsResponse(): ProjectsResponseModel {
        return ProjectsResponseModel(listOf(makeProject(), makeProject()))
    }
}