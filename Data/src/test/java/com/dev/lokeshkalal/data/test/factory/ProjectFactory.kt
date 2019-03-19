package com.dev.lokeshkalal.data.test.factory

import com.dev.lokeshkalal.data.model.ProjectEntity
import com.dev.lokeshkalal.domain.model.Project

object ProjectFactory {
    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(
            DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomBoolean()
        )
    }

    fun makeProject(): Project {
        return Project(
            DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomBoolean()
        )

    }

}