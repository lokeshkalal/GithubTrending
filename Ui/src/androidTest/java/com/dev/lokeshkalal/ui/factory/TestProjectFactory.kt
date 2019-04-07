package com.dev.lokeshkalal.ui.factory

import com.dev.lokeshkalal.presentation.model.ProjectView

object TestProjectFactory {

    fun makeProjectView(): ProjectView {
        return ProjectView(TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(), TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(), TestDataFactory.randomUuid(), TestDataFactory.randomBoolean())
    }
}