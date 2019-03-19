package com.dev.lokeshkalal.data

import com.dev.lokeshkalal.data.mapper.ProjectMapper
import com.dev.lokeshkalal.data.repository.ProjectsCache
import com.dev.lokeshkalal.data.store.ProjectsDataStoreFactory
import com.dev.lokeshkalal.domain.model.Project
import com.dev.lokeshkalal.domain.repository.ProjectsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
    private val mapper: ProjectMapper,
    private val cache: ProjectsCache,
    private val factory: ProjectsDataStoreFactory
) : ProjectsRepository {
    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(cache.isCacheExpired().toObservable(), cache.areProjectCached().toObservable(),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { isExpired, areCached ->
                Pair(areCached, isExpired)
            }).flatMap { factory.getDataStore(it.first, it.second).getProjects() }
            .flatMap { projects ->
                factory.getCachedDataStore().saveProjects(projects)
                    .andThen(Observable.just(projects))
            }.map {
                it.map { mapper.mapFromEntity(it) }
            }

    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCachedDataStore().setProjectAsBookmarkx(projectId)
    }

    override fun unBookmarkProject(projectId: String): Completable {
        return factory.getCachedDataStore().setProjectAsUnBookmarkx(projectId)
    }

    override fun getBookMarkedProjects(): Observable<List<Project>> {
        return factory.getCachedDataStore().getBookmarkedProjects().map { it.map { mapper.mapFromEntity(it) } }
    }
}