package com.dev.lokeshkalal.data.repository

import com.dev.lokeshkalal.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectsDataStore {

    fun getProjects(): Observable<List<ProjectEntity>>

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun clearProjects(): Completable

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarkx(projectId: String): Completable

    fun setProjectAsUnBookmarkx(projectId: String): Completable
}