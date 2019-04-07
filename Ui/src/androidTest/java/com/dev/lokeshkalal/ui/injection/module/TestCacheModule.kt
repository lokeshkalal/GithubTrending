package com.dev.lokeshkalal.ui.injection.module

import android.app.Application
import com.dev.lokeshkalal.cache.ProjectCacheImpl
import com.dev.lokeshkalal.cache.db.ProjectsDatabase
import com.dev.lokeshkalal.data.repository.ProjectsCache
import com.nhaarman.mockitokotlin2.mock
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
object TestCacheModule {

    @Provides
    @JvmStatic
    fun providesDataBase(application: Application): ProjectsDatabase {
        return ProjectsDatabase.getInstance(application)
    }


    @Provides
    @JvmStatic
     fun bindProjectsCache(): ProjectsCache{
        return mock()
    }
}