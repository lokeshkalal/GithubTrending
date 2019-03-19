package com.dev.lokeshkalal.domain.repository

import com.dev.lokeshkalal.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectsRepository {

    fun getProjects(): Observable<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unBookmarkProject(projectId: String): Completable

    fun getBookMarkedProjects(): Observable<List<Project>>
}