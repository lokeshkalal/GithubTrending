package com.dev.lokeshkalal.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.dev.lokeshkalal.cache.db.ProjectsDatabase
import com.dev.lokeshkalal.cache.factory.ProjectDataFactory
import com.dev.lokeshkalal.cache.mapper.CachedProjectMapper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment


@RunWith(RobolectricTestRunner::class)
class ProjectsCacheImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext,
        ProjectsDatabase::class.java
    )
        .allowMainThreadQueries()
        .build()
    private val entityMapper = CachedProjectMapper()
    private val cache = ProjectCacheImpl(database, entityMapper)

    @Test
    fun clearTablesCompletes() {
        val testObserver = cache.clearProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveProjectsCompletes() {
        val projects = listOf(ProjectDataFactory.makeProjectEntity())

        val testObserver = cache.saveProjects(projects).test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val projects = listOf(ProjectDataFactory.makeProjectEntity())
        cache.saveProjects(projects).test()

        val testObserver = cache.getProjects().test()
        testObserver.assertValue(projects)
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val bookmarkedProject = ProjectDataFactory.makeBookmarkedProjectEntity()
        val projects = listOf(
            ProjectDataFactory.makeProjectEntity(),
            bookmarkedProject
        )
        cache.saveProjects(projects).test()

        val testObserver = cache.getBookMarkedProjects().test()
        testObserver.assertValue(listOf(bookmarkedProject))
    }

    @Test
    fun setProjectAsBookmarkedCompletes() {
        val projects = listOf(ProjectDataFactory.makeProjectEntity())
        cache.saveProjects(projects).test()

        val testObserver = cache.setProjectAsBookmarked(projects[0].id).test()
        testObserver.assertComplete()
    }

    @Test
    fun setProjectAsNotBookmarkedCompletes() {
        val projects = listOf(ProjectDataFactory.makeBookmarkedProjectEntity())
        cache.saveProjects(projects).test()

        val testObserver = cache.setProjectAsUnBookmarked(projects[0].id).test()
        testObserver.assertComplete()
    }

    @Test
    fun areProjectsCacheReturnsData() {
        val projects = listOf(ProjectDataFactory.makeProjectEntity())
        cache.saveProjects(projects).test()

        val testObserver = cache.areProjectCached().test()
        testObserver.assertValue(true)
    }

    @Test
    fun setLastCacheTimeCompletes() {
        val testObserver = cache.setLastcachedTime(1000L).test()
        testObserver.assertComplete()
    }

    @Test
    fun isProjectsCacheExpiredReturnsNotExpired() {
        cache.setLastcachedTime(1000L).test()
        val testObserver = cache.isCacheExpired().test()
        testObserver.assertValue(false)
    }


}