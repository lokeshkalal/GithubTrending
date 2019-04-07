package com.dev.lokeshkalal.ui.injection.module

import com.dev.lokeshkalal.domain.repository.ProjectsRepository
import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestDataModule {

    @Provides
    @Singleton
    fun bindDataRepository(): ProjectsRepository {
        return mock()
    }
}