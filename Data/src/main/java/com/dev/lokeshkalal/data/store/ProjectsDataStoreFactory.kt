package com.dev.lokeshkalal.data.store

import com.dev.lokeshkalal.data.repository.ProjectsCache
import com.dev.lokeshkalal.data.repository.ProjectsDataStore
import io.reactivex.functions.BiFunction
import java.util.*
import javax.inject.Inject

open class ProjectsDataStoreFactory @Inject constructor(
    private val projectCachedDataStore: ProjectCachedDataStore,
    private val projectsRemoteDataStore: ProjectsRemoteDataStore
) {

    open fun getDataStore(projectsCached: Boolean, cacheExpired: Boolean): ProjectsDataStore {
        return if (projectsCached && !cacheExpired) {
            projectCachedDataStore
        } else {
            projectsRemoteDataStore
        }
    }

    open fun getCachedDataStore(): ProjectsDataStore {
        return projectCachedDataStore
    }
}