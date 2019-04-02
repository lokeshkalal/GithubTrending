package com.dev.lokeshkalal.ui.injection.module

import com.dev.lokeshkalal.data.ProjectsDataRepository
import com.dev.lokeshkalal.domain.repository.ProjectsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository): ProjectsRepository
}