package com.dev.lokeshkalal.data.repository

import com.dev.lokeshkalal.data.model.ProjectEntity
import io.reactivex.Observable

interface ProjectsRemote {

    fun getProjects(): Observable<List<ProjectEntity>>
}