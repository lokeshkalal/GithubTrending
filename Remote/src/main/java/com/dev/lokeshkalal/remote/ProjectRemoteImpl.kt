package com.dev.lokeshkalal.remote

import com.dev.lokeshkalal.data.model.ProjectEntity
import com.dev.lokeshkalal.data.repository.ProjectsRemote
import com.dev.lokeshkalal.remote.mapper.ProjectResponseModelMapper
import com.dev.lokeshkalal.remote.service.GithubTrendingService
import io.reactivex.Observable
import javax.inject.Inject

class ProjectRemoteImpl @Inject constructor(
    private val service: GithubTrendingService,
    private val mapper: ProjectResponseModelMapper
) : ProjectsRemote {


    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
            .map { it.items.map { mapper.mapFromModel(it) } }
    }
}