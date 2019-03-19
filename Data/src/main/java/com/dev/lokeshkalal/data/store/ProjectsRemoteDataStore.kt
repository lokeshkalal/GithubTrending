package com.dev.lokeshkalal.data.store

import com.dev.lokeshkalal.data.model.ProjectEntity
import com.dev.lokeshkalal.data.repository.ProjectsDataStore
import com.dev.lokeshkalal.data.repository.ProjectsRemote
import io.reactivex.Completable
import io.reactivex.Observable
import java.lang.UnsupportedOperationException
import javax.inject.Inject

open class ProjectsRemoteDataStore @Inject constructor(private val projectsRemote: ProjectsRemote) : ProjectsDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsRemote.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
       throw UnsupportedOperationException("saveProjects")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("clearProjects")
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("getBookmarkedProjects")
    }

    override fun setProjectAsBookmarkx(projectId: String): Completable {
        throw UnsupportedOperationException("setProjectAsBookmarkx")
    }

    override fun setProjectAsUnBookmarkx(projectId: String): Completable {
        throw UnsupportedOperationException("setProjectAsUnBookmarkx")
    }
}