package com.dev.lokeshkalal.data.store

import com.dev.lokeshkalal.data.model.ProjectEntity
import com.dev.lokeshkalal.data.repository.ProjectsCache
import com.dev.lokeshkalal.data.test.factory.DataFactory
import com.dev.lokeshkalal.data.test.factory.ProjectFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectCachedDataStoreTest {


    private val cache = mock<ProjectsCache>()
    private val store = ProjectCachedDataStore(cache)

    @Test
    fun getProjectsCompletes() {
        stubProjectsCacheGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        val testObserver = store.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsCacheGetProjects(Observable.just(data))
        val testObserver = store.getProjects().test()
        testObserver.assertValue(data)
    }

    @Test
    fun getProjectsCallsCacheSource() {
        stubProjectsCacheGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        store.getProjects().test()
        verify(cache).getProjects()
    }

    @Test
    fun saveProjectsCompletes() {
        stubProjectsCacheSaveProjects(Completable.complete())
        stubProjectsCacheSetLastCacheTime(Completable.complete())
        val testObserver = store.saveProjects(listOf(ProjectFactory.makeProjectEntity())).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveProjectsCallsCacheStore() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsCacheSaveProjects(Completable.complete())
        stubProjectsCacheSetLastCacheTime(Completable.complete())
        store.saveProjects(data).test()
        verify(cache).saveProjects(data)
    }

    @Test
    fun clearProjectsCompletes() {
        stubProjectsClearProjects(Completable.complete())
        val testObserver = store.clearProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearProjectsCallsCacheStore() {
        stubProjectsClearProjects(Completable.complete())
        store.clearProjects().test()
        verify(cache).clearProjects()
    }

    @Test
    fun getBookmarkedProjectsCompletes() {
        stubProjectsCacheGetBookmarkedProjects(
            Observable.just(
                listOf(
                    ProjectFactory.makeProjectEntity()
                )
            )
        )
        val testObserver = store.getBookmarkedProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsCallsCacheStore() {
        stubProjectsCacheGetBookmarkedProjects(
            Observable.just(
                listOf(
                    ProjectFactory.makeProjectEntity()
                )
            )
        )
        store.getBookmarkedProjects().test()
        verify(cache).getBookMarkedProjects()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsCacheGetBookmarkedProjects(Observable.just(data))
        val testObserver = store.getBookmarkedProjects().test()
        testObserver.assertValue(data)
    }

    @Test
    fun setProjectAsBookmarkedCompletes() {
        stubProjectsCacheSetProjectAsBookmarked(Completable.complete())
        val testObserver = store.setProjectAsBookmarkx(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun setProjectAsBookmarkedCallsCacheStore() {
        val projectId = DataFactory.randomString()
        stubProjectsCacheSetProjectAsBookmarked(Completable.complete())
        store.setProjectAsBookmarkx(projectId).test()
        verify(cache).setProjectAsBookmarked(projectId)
    }

    @Test
    fun setProjectAsNotBookmarkedCompletes() {
        stubProjectsCacheSetProjectAsNotBookmarked(Completable.complete())
        val testObserver = store.setProjectAsUnBookmarkx(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun setProjectAsNotBookmarkedCallsCacheStore() {
        val projectId = DataFactory.randomString()
        stubProjectsCacheSetProjectAsNotBookmarked(Completable.complete())
        store.setProjectAsUnBookmarkx(projectId).test()
        verify(cache).setProjectAsUnBookmarked(projectId)
    }

    private fun stubProjectsCacheGetProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(cache.getProjects())
            .thenReturn(observable)
    }

    private fun stubProjectsCacheSaveProjects(completable: Completable) {
        whenever(cache.saveProjects(any()))
            .thenReturn(completable)
    }

    private fun stubProjectsCacheSetLastCacheTime(completable: Completable) {
        whenever(cache.setLastcachedTime(any()))
            .thenReturn(completable)
    }

    private fun stubProjectsClearProjects(completable: Completable) {
        whenever(cache.clearProjects())
            .thenReturn(completable)
    }

    private fun stubProjectsCacheGetBookmarkedProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(cache.getBookMarkedProjects())
            .thenReturn(observable)
    }

    private fun stubProjectsCacheSetProjectAsBookmarked(completable: Completable) {
        whenever(cache.setProjectAsBookmarked(any()))
            .thenReturn(completable)
    }

    private fun stubProjectsCacheSetProjectAsNotBookmarked(completable: Completable) {
        whenever(cache.setProjectAsUnBookmarked(any()))
            .thenReturn(completable)
    }
}