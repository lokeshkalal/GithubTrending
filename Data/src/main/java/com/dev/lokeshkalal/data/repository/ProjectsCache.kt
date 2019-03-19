package com.dev.lokeshkalal.data.repository

import com.dev.lokeshkalal.data.model.ProjectEntity
import com.dev.lokeshkalal.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ProjectsCache {

    fun clearProjects(): Completable

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun getProjects(): Observable<List<ProjectEntity>>

    fun getBookMarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsUnBookmarked(projectId: String): Completable

    fun areProjectCached(): Single<Boolean>

    fun setLastcachedTime(lastCache: Long): Completable

    fun isCacheExpired(): Single<Boolean>
}