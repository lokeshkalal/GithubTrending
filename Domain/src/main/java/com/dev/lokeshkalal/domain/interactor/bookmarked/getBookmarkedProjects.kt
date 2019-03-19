package com.dev.lokeshkalal.domain.interactor.bookmarked

import com.dev.lokeshkalal.domain.executor.PostExecutionThread
import com.dev.lokeshkalal.domain.interactor.ObservableUseCase
import com.dev.lokeshkalal.domain.model.Project
import com.dev.lokeshkalal.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

open class getBookmarkedProjects @Inject constructor(
    private val projectsRepository: ProjectsRepository
    , val postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {
    public override fun buildUseCaseobservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getBookMarkedProjects()
    }
}