package com.dev.lokeshkalal.data.store

import com.nhaarman.mockitokotlin2.mock
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class ProjectsDataStoreFactoryTest {

    private val cachedStore = mock<ProjectCachedDataStore>()
    private val remoteStore = mock<ProjectsRemoteDataStore>()
    private val projectsDataStoreFactory = ProjectsDataStoreFactory(cachedStore, remoteStore)

    @Test
    fun getDataStoreReturnRemoteStoreWhenCacheExpired() {
        assertEquals(remoteStore, projectsDataStoreFactory.getDataStore(projectsCached = true, cacheExpired = true))

    }

    @Test
    fun getDataStoreReturnRemoteStoreWhenProjectsNotCached() {
        assertEquals(remoteStore, projectsDataStoreFactory.getDataStore(projectsCached = false, cacheExpired = false))

    }

    @Test
    fun getDataStoreReturnCacheStore() {
        assertEquals(cachedStore, projectsDataStoreFactory.getDataStore(projectsCached = true, cacheExpired = false))

    }

    @Test
    fun getCacheDataStoreReturnCacheStore() {
        assertEquals(cachedStore, projectsDataStoreFactory.getCachedDataStore())

    }
}