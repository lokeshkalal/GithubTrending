package com.dev.lokeshkalal.data.store

import com.dev.lokeshkalal.data.model.ProjectEntity
import com.dev.lokeshkalal.data.repository.ProjectsCache
import com.dev.lokeshkalal.data.repository.ProjectsDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

open class ProjectCachedDataStore @Inject constructor(private val projectsCache: ProjectsCache) : ProjectsDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectsCache.saveProjects(projects)
            .andThen(projectsCache.setLastcachedTime(System.currentTimeMillis()))
    }

    override fun clearProjects(): Completable {
        return projectsCache.clearProjects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getBookMarkedProjects()
    }

    override fun setProjectAsBookmarkx(projectId: String): Completable {
        return projectsCache.setProjectAsBookmarked(projectId)
    }

    override fun setProjectAsUnBookmarkx(projectId: String): Completable {
        return projectsCache.setProjectAsUnBookmarked(projectId)
    }
}