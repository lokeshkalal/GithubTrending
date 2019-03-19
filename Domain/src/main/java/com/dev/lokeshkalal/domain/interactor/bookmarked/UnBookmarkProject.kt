package com.dev.lokeshkalal.domain.interactor.bookmarked

import com.dev.lokeshkalal.domain.executor.PostExecutionThread
import com.dev.lokeshkalal.domain.interactor.CompletableUseCase
import com.dev.lokeshkalal.domain.repository.ProjectsRepository
import io.reactivex.Completable
import javax.inject.Inject

open class UnBookmarkProject @Inject constructor(
    private val projectsRepository: ProjectsRepository
    , val postExecutionThread: PostExecutionThread
) : CompletableUseCase<UnBookmarkProject.Params>(postExecutionThread) {

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null)
            throw IllegalArgumentException("params cant be null")
        return projectsRepository.unBookmarkProject(params.projectId)
    }

    data class Params constructor(val projectId: String) {
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }

    }
}