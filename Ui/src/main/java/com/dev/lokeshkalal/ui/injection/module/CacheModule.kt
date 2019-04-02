package com.dev.lokeshkalal.ui.injection.module

import android.app.Application
import com.dev.lokeshkalal.cache.ProjectCacheImpl
import com.dev.lokeshkalal.cache.db.ProjectsDatabase
import com.dev.lokeshkalal.data.repository.ProjectsCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): ProjectsDatabase {
            return ProjectsDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: ProjectCacheImpl): ProjectsCache
}