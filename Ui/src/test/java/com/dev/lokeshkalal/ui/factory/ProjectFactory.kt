package com.dev.lokeshkalal.ui.factory

import com.dev.lokeshkalal.presentation.model.ProjectView

object ProjectFactory {

    fun makeProjectView(): ProjectView {
        return ProjectView(DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomBoolean())
    }
}