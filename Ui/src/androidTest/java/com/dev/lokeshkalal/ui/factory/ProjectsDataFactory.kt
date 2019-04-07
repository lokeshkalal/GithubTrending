package com.dev.lokeshkalal.ui.factory

import com.dev.lokeshkalal.domain.model.Project

object ProjectsDataFactory {
    fun makeProject(): Project {
        return Project(TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(), TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(), TestDataFactory.randomUuid(), TestDataFactory.randomBoolean())
    }
}