package com.dev.lokeshkalal.cache

import com.dev.lokeshkalal.cache.db.ProjectsDatabase
import com.dev.lokeshkalal.cache.mapper.CachedProjectMapper
import com.dev.lokeshkalal.cache.model.Config
import com.dev.lokeshkalal.data.model.ProjectEntity
import com.dev.lokeshkalal.data.repository.ProjectsCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle
import javax.inject.Inject

class ProjectCacheImpl @Inject constructor(
    private val projectsDatabase: ProjectsDatabase,
    private val mapper: CachedProjectMapper
) : ProjectsCache {
    override fun clearProjects(): Completable {

        return Completable.defer {
            projectsDatabase.cachedProjectsDao().deleteProjects()
            Completable.complete()
        }

    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().insertProjects(
                projects.map { mapper.mapToCached(it) }
            )
            Completable.complete()
        }

    }

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getProjects().toObservable()
            .map { it.map { mapper.mapFromcached(it) } }
    }

    override fun getBookMarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getBookmarkedProjects().toObservable()
            .map { it.map { mapper.mapFromcached(it) } }

    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(true, projectId)
            Completable.complete()
        }
    }

    override fun setProjectAsUnBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(false, projectId)
            Completable.complete()
        }
    }

    override fun areProjectCached(): Single<Boolean> {
        return projectsDatabase.cachedProjectsDao().getProjects().isEmpty
    }

    override fun setLastcachedTime(lastCache: Long): Completable {
        return Completable.defer {
            projectsDatabase.configDao().insertConfig(Config(lastCachedTime = lastCache))
            Completable.complete()
        }
    }

    override fun isCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        return projectsDatabase.configDao().getConfig()
            .single(Config(lastCachedTime = 0))
            .map { currentTime - it.lastCachedTime > expirationTime }
    }
}