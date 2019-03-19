package com.dev.lokeshkalal.domain.interactor.browse

import com.dev.lokeshkalal.domain.executor.PostExecutionThread
import com.dev.lokeshkalal.domain.interactor.ObservableUseCase
import com.dev.lokeshkalal.domain.model.Project
import com.dev.lokeshkalal.domain.repository.ProjectsRepository
import io.reactivex.Observable
import io.reactivex.internal.operators.observable.ObservableAll
import io.reactivex.internal.operators.observable.ObservableUsing
import javax.inject.Inject

open class GetProjects @Inject constructor(
    val projectsRepository: ProjectsRepository,
    val postExecutionThread: PostExecutionThread
) :
    ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {
    public override fun buildUseCaseobservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }
}